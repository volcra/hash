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

/**
 * Test class for {@link HashBannerProvider}.
 */
class HashBannerProviderTest extends GroovyTestCase {
    def provider = new HashBannerProvider()

    /**
     * Test method for {@lunk HashBannerProvider#banner}.
     */
    void testBanner() {
        println provider.banner
    }

    /**
     * Test method for {@link HashBannerProvider#getVersion()}.
     */
    void testVersion() {
        assert provider.version == '0.0.1-SNAPSHOT'
    }

    /**
     * Test method for {@link HashBannerProvider#getWelcomeMessage()}.
     */
    void testWelcomeMessage() {
        assert provider.welcomeMessage ==
            "Welcome to ${provider.name()} $provider.version.\nFor assistance press or type \"help\" then hit ENTER."
    }

    /**
     * Test method for {@link HashBannerProvider#name()}
     */
    void testName() {
        assert provider.name() == '#Shell'
    }
}