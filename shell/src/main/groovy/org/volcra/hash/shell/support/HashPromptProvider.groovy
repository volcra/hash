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
import org.springframework.shell.plugin.PromptProvider
import org.springframework.stereotype.Component

/**
 * Prompt Provider Implementation.
 *
 * <p>Set prompt to {@code # >}</p>
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
@CompileStatic
@Order(Ordered.HIGHEST_PRECEDENCE)
class HashPromptProvider implements PromptProvider {

    /**
     * Prompt value #>.
     */
    String prompt = '#>'

    /**
     * The name of this plugin.
     * @return the name of the plugin
     */
    String name() {
        '# prompt provider'
    }
}
