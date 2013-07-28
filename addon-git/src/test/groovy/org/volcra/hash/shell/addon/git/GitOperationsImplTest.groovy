package org.volcra.hash.shell.addon.git

import org.eclipse.jgit.api.Git

/**
 *
 * @author Emanuelle Gardu&ntilde;o
 */
class GitOperationsImplTest extends GroovyTestCase {
    private static final String REPO_URI = 'git://github.com/components/jquery.git'
    private static Git git

    def fixture = new GitOperationsImpl()

    void setUp() {
        if (!git) {
            def directory = new File(new File('.'), 'addon-git/build/jquery')
            if (directory.exists()) directory.deleteDir()

            git = fixture.clone directory, REPO_URI
        }
    }

    void testClone() {
        assert git != null
    }

    void testFetch() {
        fixture.fetch git.repository
    }

    void testCheckout() {
        fixture.checkout git.repository, fixture.tags(git.repository).head().name
    }

    void testBranches() {
        def branches = fixture.branches git.repository

        assert branches
    }

    void testTags() {
        def tags = fixture.tags git.repository

        assert tags
    }
}
