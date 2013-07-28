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
package org.volcra.hash.shell.addon.bower

/**
 * Bower Operations.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
interface BowerOperations {
    /**
     * Version info and description of a particular package.
     *
     * @param name
     */
    void info(String name)

    /**
     * Install a package locally.
     *
     * @param name package name
     * @param version package version
     */
    void install(String name, String version)

    void list()

    void lookup(String name)

    void search(String name)

    void uninstall(String name)
}
