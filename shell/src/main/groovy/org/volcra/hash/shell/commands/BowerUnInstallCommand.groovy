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

import groovy.transform.CompileStatic
import org.springframework.beans.factory.annotation.Value
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component

/**
 * Uninstalls a package.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
@CompileStatic
class BowerUnInstallCommand implements CommandMarker {
    /**
     * Components root folder.
     */
    @Value("#{shellProperties['components.dir']}")
    File components

    /**
     * Uninstalls a package.
     *
     * @param name name of the package
     * @return confirmation message
     */
    @CliCommand(value = 'bower uninstall', help = 'Remove a package')
    String uninstall(
            @CliOption(key = ['name', ''], mandatory = true, help = 'The package to remove') String name) {
        def pkg = new File(components, name)

        if (pkg.exists()) {
            pkg.deleteDir()
            "Package $name deleted"
        } else
            "Package $name is not installed. You may use bower list to show all current installed packages"
    }
}
