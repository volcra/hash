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

import org.eclipse.jgit.lib.Ref
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.HashColorLogger
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component
import org.volcra.hash.shell.bower.BowerRegistry
import org.volcra.hash.shell.git.GitRepository

/**
 * Version info and description of a particular package
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
class BowerInfoCommand implements CommandMarker {

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

    @CliCommand(value = 'bower info', help = 'Version info and description of a particular package')
    void info(@CliOption(key = ['name', ''], mandatory = true, help = 'Package name') String name) {
        def pkg = bower.find name

        if (pkg) {
            def gitRepository = new GitRepository(pkg.name as String, pkg.website as String, new File(".hash/$name"))

            colorLogger.cyan name printNewline()
            println '\nVersions: '

            gitRepository.tagList().each { Ref ref ->
                println "- ${ref.name.substring(ref.name.lastIndexOf('/') + 1)}"
            }
        } else
            colorLogger.cyan name yellow ' was not found' printNewline()
    }
}