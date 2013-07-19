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

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.SimpleClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

/**
 * Spring configuration and bootstrapping of components.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Configuration
class AppConfig {
    /**
     * http.proxyHost key name to retrieve from propeties.
     */
    private static final String HTTP_PROXY_HOST = 'http.proxyHost'

    /**
     * REST Template used to call the Bower repository.
     *
     * <p>If proxy is enabled by {@code http.proxyHost} it will configure the proxy through the {@code
     * SimpleClientHttpRequestFactory}. By default the proxy port is 80.
     *
     * @return the rest template object
     */
    @Bean
    RestTemplate getRestTemplate() {
        if (proxy) new RestTemplate(new SimpleClientHttpRequestFactory(proxy: proxy))
        else new RestTemplate()
    }

    /**
     * Proxy, if no proxy is configured will return {@literal null}.
     * @return the proxy or null if no proxy is set up
     */
    @Bean
    Proxy getProxy() {
        def env = System.properties

        if (env[HTTP_PROXY_HOST])
            new Proxy(Proxy.Type.HTTP, new InetSocketAddress(env[HTTP_PROXY_HOST].toString(),
                    (env['http.proxyPort'] ?: 80) as Integer))
        else null
    }
}
