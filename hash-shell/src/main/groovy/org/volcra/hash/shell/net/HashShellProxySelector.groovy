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
package org.volcra.hash.shell.net

import groovy.transform.CompileStatic
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Concrete class of {@link ProxySelector} used to clone repositories with JGit.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
@CompileStatic
class HashShellProxySelector extends ProxySelector implements InitializingBean {
    /**
     * Proxy settings.
     *
     * <p>May be null if no proxy is set up.
     */
    @Autowired
    Proxy proxy

    @Override
    List<Proxy> select(URI uri) {
        [proxy]
    }

    @Override
    void connectFailed(URI uri, SocketAddress sa, IOException ioe) {
    }

    /**
     * Sets this instance as ProxySelector default if {@link HashShellProxySelector#proxy} is not null.
     * @throws Exception
     */
    @Override
    void afterPropertiesSet() throws Exception {
        if (proxy) ProxySelector.default = this
    }
}
