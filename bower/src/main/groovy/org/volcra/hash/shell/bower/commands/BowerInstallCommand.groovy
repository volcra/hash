/*
 * Copyright 2013 Volcra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.volcra.hash.shell.bower.commands

import groovy.io.FileType
import groovy.json.JsonSlurper
import groovy.util.logging.Log
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.HashColorLogger
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component
import org.volcra.hash.shell.bower.BowerRegistry
import org.volcra.hash.shell.git.GitRepository

/**
 * Package Installer.
 *
 * <p>Installs a package by given a package name and optional version.</p>
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Log
@Component
class BowerInstallCommand implements CommandMarker {
    /**
     * Constant to print the command output.
     */
    private static final String BOWER_CMD_OUT = '  bower '

    /**
     * Components root folder.
     */
    @Value("#{shellProperties['components.dir']}")
    File components

    /**
     * Bower Registry.
     */
    @Autowired
    BowerRegistry bower

    /**
     * Utility class to log to the console with colors.
     */
    @Autowired
    HashColorLogger colorLogger

    /**
     * Installs a package.
     *
     * @param name of the package to install
     * @param version optional version of the package, default to latest
     */
    // TODO Bower supports multiple patterns for package names, implement
    @CliCommand(value = 'bower install', help = 'Install a package locally')
    void install(
            @CliOption(key = ['name', ''], mandatory = true, help = 'The package name to install') String name,
            @CliOption(key = 'version', help = 'Package version, if not provided will use default or master branch',
                    unspecifiedDefaultValue = 'latest') String version) {
        def pkg = bower.find name

        if (pkg) {
            process name, version, pkg
            colorLogger.cyan name green ' installed' printNewline()
        } else
            colorLogger.cyan name yellow ' was not found' printNewline()
    }

    /**
     * Processes the git repository looking for JSON files matching (bower|component) and copying the content of the
     * repository to that folder.
     *
     * @param git repository
     */
    private void process(String name, String version, pkg) {
        colorLogger.log BOWER_CMD_OUT cyan 'cloning ' log pkg.website printNewline()
        def git = new GitRepository(name, pkg.website as String, new File(".hash/$name"))
        def tag = version == null || version == 'latest' ? git.tagList().last().name : version

        colorLogger.log BOWER_CMD_OUT cyan 'fetching ' log name printNewline()
        git.fetch()
        colorLogger.log BOWER_CMD_OUT cyan 'checking out ' log "$name#$tag" printNewline()
        git.checkout tag

        def repository = git.repository.directory.parentFile
        def component = new File(components, repository.name)

        component.mkdirs()

        def slurper = new JsonSlurper()
        repository.eachFileMatch(FileType.FILES, ~/(bower|component)\.json/) {
            def json = slurper.parseText it.text

            if (json.dependencies)
                json.dependencies.each {
                    println "${it.key}->${it.value}"
                    install it.key as String, it.value as String
                }
        }

        colorLogger.log BOWER_CMD_OUT cyan 'installing ' log "$name#$git.repository.fullBranch" printNewline()
        FileUtils.copyDirectory repository, component
        FileUtils.deleteDirectory new File(component, '.git')
    }
}
