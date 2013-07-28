package org.volcra.hash.shell.addon.git

import org.eclipse.jgit.api.*
import org.eclipse.jgit.lib.Ref
import org.eclipse.jgit.lib.Repository
import org.eclipse.jgit.transport.FetchResult
import org.springframework.stereotype.Component

/**
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
class GitOperationsImpl implements GitOperations {
    @Override
    Git clone(String uri) {
        new CloneCommand(uri: uri).call()
    }

    @Override
    Git clone(File directory, uri) {
        new CloneCommand(directory: directory, uri: uri).call()
    }

    @Override
    FetchResult fetch(Repository repository) {
        new FetchCommand(repository).call()
    }

    @Override
    Ref checkout(Repository repository, String name) {
        new CheckoutCommand(repository).setName name call()
    }

    @Override
    List<Ref> branches(Repository repository) {
        new ListBranchCommand(repository).call()
    }

    @Override
    List<Ref> tags(Repository repository) {
        new ListTagCommand(repository).call()
    }
}
