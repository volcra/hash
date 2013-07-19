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

/**
 * Test class for {#link GitCommand}.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
class GitCommandTest extends GroovyTestCase {
    def fixture = new GitCommand()

    /**
     * Test method for {@link GitCommand#clone(java.lang.String, java.io.File)}.
     */
    void testClone() {
        fixture.execute 'git@github.com:volcra/hash-coffee.git', null
    }
}
