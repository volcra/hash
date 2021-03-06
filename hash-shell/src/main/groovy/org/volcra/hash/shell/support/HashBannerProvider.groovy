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
package org.volcra.hash.shell.support

import groovy.transform.CompileStatic
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.shell.plugin.BannerProvider
import org.springframework.stereotype.Component

/**
 * Banner Provider.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
@CompileStatic
@Order(Ordered.HIGHEST_PRECEDENCE)
class HashBannerProvider implements BannerProvider {

    /**
     * Hash Shell banner.
     */
    final String banner = """\
-------------------------------------------------------------------------------
                                _       __ _          _ _
                 /\\  /\\__ _ ___| |__   / _\\ |__   ___| | |
                / /_/ / _` / __| '_ \\  \\ \\| '_ \\ / _ \\ | |
               / __  / (_| \\__ \\ | | | _\\ \\ | | |  __/ | |
               \\/ /_/ \\__,_|___/_| |_| \\__/_| |_|\\___|_|_|

-------------------------------------------------------------------------------
"""

    /**
     * Version.
     */
    // TODO move to external file
    final String version = '0.0.1-SNAPSHOT'

    /**
     * Welcome Message.
     */
    final String welcomeMessage =
        "Welcome to ${name()} $version.\nFor assistance press or type \"help\" then hit ENTER."

    /**
     * Name of this plugin.
     * @return "#Shell"
     */
    String name() {
        '#Shell'
    }
}