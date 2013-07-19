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
package org.volcra.hash.shell.spring

import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

/**
 * Spring application context text.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@RunWith(SpringJUnit4ClassRunner)
@ContextConfiguration(['classpath:/META-INF/spring/spring-shell-plugin.xml'])
class AppConfigTest {
    /**
     * Spring context.
     */
    @Autowired
    ApplicationContext ctx

    /**
     * Application Context should be loaded and injected into {@link AppConfigTest#ctx}
     */
    @Test
    void testContext() {
        assert ctx != null, 'Context must not be null'
    }
}
