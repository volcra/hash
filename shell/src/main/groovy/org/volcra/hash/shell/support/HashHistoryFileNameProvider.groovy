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
import org.springframework.beans.factory.InitializingBean
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.shell.plugin.HistoryFileNameProvider
import org.springframework.stereotype.Component

/**
 * History File Name Provider.
 */
@Component
@CompileStatic
@Order(Ordered.HIGHEST_PRECEDENCE)
class HashHistoryFileNameProvider implements HistoryFileNameProvider, InitializingBean {
    /**
     * Directory name.
     */
    final static String HISTORY_DIRECTORY = '.hash'

    /**
     * Name of the history file.
     */
    final String historyFileName = "$HISTORY_DIRECTORY/hash-shell.log"

    /**
     * Name of this plugin.
     * @return "hash shell file name provider"
     */
    String name() {
        'hash shell file name provider'
    }

    /**
     * Creates the directory and log file in case hey don't exist.
     *
     * @throws Exception
     */
    @Override
    void afterPropertiesSet() throws Exception {
        new File(HISTORY_DIRECTORY).mkdirs()
        new File(historyFileName).createNewFile()
    }
}
