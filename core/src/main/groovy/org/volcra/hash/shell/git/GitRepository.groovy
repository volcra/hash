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
package org.volcra.hash.shell.git

import org.eclipse.jgit.api.CloneCommand
import org.eclipse.jgit.api.Git
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.storage.file.FileRepository

/**
 *
 * @author 501230882
 */
class GitRepository {
    private Git git
    Repository repository

    final String name
    final String uri
    final File directory

    GitRepository(String name, String uri, File directory) {
        this.name = name
        this.uri = "${uri}.git"
        this.directory = directory

        if (directory.exists()) {
            repository = new FileRepository(new File(directory, '.git'))
            git = new Git(repository)
        } else cloneRepository()
    }

    def fetch() {
        git.fetch().call()
    }

    def checkout(String name) {
        git.checkout() setName name call()
    }

    def tagList() {
        git.tagList() call()
    }

    def branchList() {
        git.branchList() call()
    }

    void cloneRepository() {
        git = new CloneCommand(directory: directory, uri: uri).call()
        repository = git.repository
    }
}
