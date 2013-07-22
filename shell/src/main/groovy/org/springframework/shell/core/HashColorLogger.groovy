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
package org.springframework.shell.core

import groovy.transform.CompileStatic
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Logger class that prints messages to {@code jline.ConsoleReader} in color.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
@CompileStatic
class HashColorLogger implements InitializingBean {
    /**
     * Spring shell component, required to access {@code jline.ConsoleReader} and send color messages.
     */
    @Autowired
    JLineShellComponent shell

    /**
     * Logs a message in magenta.
     *
     * @param message message to print
     * @return a reference to self
     */
    HashColorLogger magenta(String message) {
        shell.reader.printString JLineLogHandler.ANSIBuffer.magenta(message).toString()
        this
    }

    /**
     * Logs a message in yellow.
     *
     * @param message message to print
     * @return a reference to self
     */
    HashColorLogger yellow(String message) {
        shell.reader.printString JLineLogHandler.ANSIBuffer.yellow(message).toString()
        this
    }

    /**
     * Logs a message in green.
     *
     * @param message message to print
     * @return a reference to self
     */
    HashColorLogger green(String message) {
        shell.reader.printString JLineLogHandler.ANSIBuffer.green(message).toString()
        this
    }

    /**
     * Logs a message in cyan.
     *
     * @param message message to print
     * @return a reference to self
     */
    HashColorLogger cyan(String message) {
        shell.reader.printString JLineLogHandler.ANSIBuffer.cyan(message).toString()
        this
    }

    /**
     * Logs a message in blue.
     *
     * @param message message to print
     * @return a reference to self
     */
    HashColorLogger blue(String message) {
        shell.reader.printString JLineLogHandler.ANSIBuffer.blue(message).toString()
        this
    }

    /**
     * Logs a message in red.
     *
     * @param message message to print
     * @return a reference to self
     */
    HashColorLogger red(String message) {
        shell.reader.printString JLineLogHandler.ANSIBuffer.red(message).toString()
        this
    }

    /**
     * Logs a message in black.
     *
     * @param message message to print
     * @return a reference to self
     */
    HashColorLogger black(String message) {
        shell.reader.printString JLineLogHandler.ANSIBuffer.black(message).toString()
        this
    }

    /**
     * Logs a message with the default color.
     *
     * @param message message to print
     * @return a reference to self
     */
    HashColorLogger log(String message) {
        shell.reader.printString message
        this
    }

    /**
     * Adds a new line to the reader and flushes the console.
     */
    void printNewline() {
        shell.reader.printNewline()
        shell.reader.flushConsole()
    }

    @Override
    void afterPropertiesSet() throws Exception {
        assert shell != null, 'shell must not be null, it should be provided from the Spring Context'
    }
}
