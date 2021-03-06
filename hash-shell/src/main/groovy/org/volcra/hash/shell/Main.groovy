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
package org.volcra.hash.shell

import groovy.transform.CompileStatic
import org.springframework.shell.Bootstrap

/**
 * Main class.
 *
 * <p>Mainly delegates to {@code Bootstrap}.
 */
@CompileStatic
class Main {
    /**
     * Main method.
     *
     * @param args command line arguments
     */
    // TODO in future releases, plan to have direct access to the Command without using Spring shell
    static void main(String... args) {
        Bootstrap.main args
    }
}
