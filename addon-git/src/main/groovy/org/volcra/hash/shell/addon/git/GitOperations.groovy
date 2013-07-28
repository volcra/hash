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
package org.volcra.hash.shell.addon.git

import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.transport.FetchResult

/**
 *
 * @author Emanuelle Gardu&ntilde;o
 */
public interface GitOperations {
    /**
     * Clone a repository.
     *
     * @param uri repository uri
     * @return Git repository
     */
    Git clone(String uri)

    Git clone(File directory, uri)

    FetchResult fetch(Repository repository)

    Ref checkout(Repository repository, String name)

    List<Ref> branches(Repository repository)

    List<Ref> tags(Repository repository)
}