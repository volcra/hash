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
package org.volcra.hash.shell.commands

import org.volcra.hash.shell.support.HashBannerProvider

/**
 * Test class for {@link VersionCommand}.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
class VersionCommandTest extends GroovyTestCase {
    /**
     * Test Fixture.
     */
    def command = new VersionCommand()

    /**
     * Expected version number.
     */
    // TODO move to a properties file or external source
    static final VERSION = '0.0.1-SNAPSHOT'

    /**
     * Test set up.
     */
    void setUp() {
        command.banner = [version: VERSION] as HashBannerProvider
    }

    /**
     * Test method for {@link VersionCommand#version()}
     */
    void testVersion() {
        assert "#Shell $VERSION" == command.version()
    }
}