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
package org.volcra.hash.shell.commands

import groovy.io.FileType
import groovy.json.JsonSlurper
import groovy.util.logging.Log
import org.apache.commons.io.FileUtils
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.storage.file.FileRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.HashColorLogger
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component
import org.springframework.util.StopWatch
import org.volcra.hash.shell.bower.BowerRegistry

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
     * Helper class to clone the repository.
     */
    @Autowired
    GitCommand gitCommand

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
    String install(
            @CliOption(key = ['name', ''], mandatory = true, help = 'The package name to install') String name,
            @CliOption(key = 'version', help = 'Package version, if not provided will use default or master branch',
                    unspecifiedDefaultValue = 'latest') String version) {
        def stopWatch = new StopWatch()
        stopWatch.start()
        def pkg = bower.find name

        if (pkg) {
            process name, checkout(name, pkg.website as String, version)
            stopWatch.stop()

            "Package $name installed in ${stopWatch.totalTimeSeconds} seconds"
        } else
            "Package $name not found"
    }

    /**
     * Processes the git repository looking for JSON files matching (bower|component) and extracting the main
     * to the components folder.
     *
     * @param git repository
     */
    private void process(String name, Git git) {
        def slurper = new JsonSlurper()
        def repository = git.repository.directory.parentFile
        def component = new File(components, repository.name)

        component.mkdirs()

        repository.eachFileMatch(FileType.FILES, ~/(bower|component)\.json/) {
            def json = slurper.parseText it.text

            if (json.dependencies)
                json.dependencies.each {
                    println "${it.key}->${it.value}"
                    install it.key as String, it.value as String
                }
        }


        colorLogger.log BOWER_CMD_OUT cyan 'installing ' log "$name#$git.repository.branch" printNewline()
        FileUtils.copyDirectory repository, component
        FileUtils.deleteDirectory new File(component, '.git')
    }

    /**
     * Checks outs the given {@code version} from the repository {@code name} with {@code version} using latest as
     * default.
     *
     * @param name repository name
     * @param url repository url
     * @param version version to checkout, latest by default
     * @return a reference to the Git repository
     */
    private Git checkout(String name, String url, String version) {
        colorLogger.log BOWER_CMD_OUT cyan 'cloning ' log url printNewline()
        def git = getRepository name, url
        def tag = version == null || version == 'latest' ? git.tagList().call().last().name : version

        colorLogger.log BOWER_CMD_OUT cyan 'fetching ' log name printNewline()
        git.fetch() call()
        colorLogger.log BOWER_CMD_OUT cyan 'checking out ' log "$name#$tag" printNewline()
        git.checkout() setName tag call()
        git
    }

    /**
     * Clones a repository or returns an existing repository.
     *
     * @param name repository name
     * @param url repository url
     * @return the Git Repository
     */
    private Git getRepository(String name, String url) {
        def repository = new File(".hash/$name/.git")

        if (repository.exists()) new Git(new FileRepository(repository))
        else gitCommand.clone "${url}.git", null
    }
}
