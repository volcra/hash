/*
 * Copyright 2013 Volcra
 *
 * Licensed under the Apache License, Version 2.0 (the 'License');
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an 'AS IS' BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.volcra.hash.shell.commands

import groovy.transform.CompileStatic
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
class UnInstallCommand implements CommandMarker {
    /**
     * Uninstalls a package. If version is not given it will uninstall the only version available in the components.
     * @param name name of the package
     * @param version version to uninstall
     * @return confirmation message
     */
    @CliCommand(value = 'uninstall', help = 'Uninstalls a package')
    String uninstall(
            @CliOption(key = ['name'], mandatory = true, help = 'The command name to uninstall') String name) {
        "Uninstalling command $name"
    }
}
