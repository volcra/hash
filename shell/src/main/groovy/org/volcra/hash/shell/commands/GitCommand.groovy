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

import groovy.transform.CompileStatic
import org.eclipse.jgit.api.CloneCommand
import org.eclipse.jgit.api.Git
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component

/**
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
@CompileStatic
class GitCommand implements CommandMarker {
    @CliCommand(value = 'git clone', help = 'Clones a Git Repository')
    String execute(@CliOption(key = 'uri', mandatory = true, help = 'The Repository url') String uri,
                   @CliOption(key = 'directory') File directory) {
        clone uri, directory

        "Repository with URI $uri has been cloned"
    }

    /**
     * Clones a repository.
     *
     * @param uri repository uri
     * @param directory target directory
     * @return a {@link Git} object
     */
    Git clone(String uri, File directory) {
        def clone = new CloneCommand()
        def matcher = uri.trim() =~ /^.+\/([A-Za-z0-9\-]+)\.git?$/

        if (directory == null && matcher.matches()) clone.directory = new File(new File('.hash'), matcher.group(1))
        else clone.directory = directory

        clone.uri = uri.trim()
        clone.call()
    }
}
