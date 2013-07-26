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
package org.volcra.hash.shell.addon.bower

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component

/**
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
class BowerCommands implements CommandMarker {
    @Autowired
    BowerOperations bowerOperations

    @CliCommand(value = 'bower info', help = 'Version info and description of a particular package')
    void info(@CliOption(key = ['name', ''], mandatory = true, help = 'Package name') String name) {
        bowerOperations.info name
    }

    @CliCommand(value = 'bower install', help = 'Install a package locally')
    void install(
            @CliOption(key = ['name', ''], mandatory = true, help = 'The package name to install') String name,
            @CliOption(key = 'version', help = 'Package version, if not provided will use default or master branch',
                    unspecifiedDefaultValue = 'latest') String version) {
        bowerOperations.install name, version
    }

    @CliCommand(value = 'bower list', help = 'List all installed packages')
    void list() {
        bowerOperations.list()
    }

    @CliCommand(value = 'bower lookup', help = 'Look up a package URL by name')
    void lookup(@CliOption(key = ['name', ''], mandatory = true, help = 'The package name to lookup') String name) {
        bowerOperations.lookup name
    }

    @CliCommand(value = 'bower search', help = 'Searches for packages in the repository')
    void search(@CliOption(key = ['name', ''], mandatory = true, help = 'The name of the package') String name) {
        bowerOperations.search name
    }

    @CliCommand(value = 'bower uninstall', help = 'Remove a package')
    void uninstall(
            @CliOption(key = ['name', ''], mandatory = true, help = 'The package to delete') String name) {
        bowerOperations.uninstall name
    }

}