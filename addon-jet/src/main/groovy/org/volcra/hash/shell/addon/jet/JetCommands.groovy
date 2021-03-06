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
package org.volcra.hash.shell.addon.jet

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliAvailabilityIndicator
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.stereotype.Component

/**
 * Jet Plugin, starts a jetty web server in the specified location.
 */
@Component
class JetCommands implements CommandMarker {
    @Autowired
    JetOperations jetOperations

    @CliCommand(value = 'jet start', help = 'Starts a jet web server')
    void start() {
        jetOperations.start()
    }

    @CliCommand(value = 'jet stop', help = 'Stops a jet web server')
    void stop() {
        jetOperations.stop();
    }

    @CliAvailabilityIndicator(['jet stop'])
    boolean isJetStopAvailable() {
        jetOperations.isRunning();
    }
}
