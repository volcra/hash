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

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.HashColorLogger
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component
import org.volcra.hash.shell.bower.BowerRegistry

/**
 * Bower Lookup command.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
class BowerLookupCommand implements CommandMarker {
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
     * Look up a package URL by name.
     *
     * @param name package name
     */
    @CliCommand(value = 'bower lookup', help = 'Look up a package URL by name')
    void lookup(@CliOption(key = ['name', ''], mandatory = true, help = 'The package name to lookup') String name) {
        def pkg = bower.find name

        if (pkg) colorLogger.cyan "$pkg.name " log pkg.website printNewline()
        else colorLogger.cyan name yellow ' was not found' printNewline()
    }
}
