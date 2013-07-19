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
package org.volcra.hash.shell.bower

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import org.springframework.web.client.RestTemplate

/**
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Repository
class BowerRegistryImpl implements BowerRegistry {
    /**
     * Cache the bower response in a map.
     */
    private final static Map CACHE = [:]

    /**
     * Constant bower.
     */
    private final static String BOWER = 'bower'

    /**
     * Bower endpoint.
     */
    @Value("#{shellProperties['bower.url']}")
    String endpoint

    /**
     * Rest Template to call Bower.
     */
    @Autowired
    RestTemplate restTemplate

    /**
     * Calls the bower registry to retrieve all available packages.
     *
     * @return JSON response as String
     */
    private String bowerComponentList() {
        if (CACHE.containsKey(BOWER)) CACHE[BOWER]
        else CACHE[BOWER] = restTemplate.getForObject endpoint, String
    }

    /**
     * Parses the bower JSON response with a {@code JsonSlurper}.
     *
     * @return JSON
     */
    private jsonBowerList() {
        new JsonSlurper().parseText bowerComponentList()
    }

    @Override
    def find(String name) {
        jsonBowerList().find { it.name == name }
    }

    @Override
    Iterable find(Closure filter) {
        jsonBowerList().findAll filter
    }
}
