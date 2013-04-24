package org.volcra.hash.shell.commands

import org.volcra.hash.shell.support.HashBannerProvider


class VersionCommandTest extends GroovyTestCase {
    def command = new VersionCommand()

    static version = "0.0.1-SNAPSHOT"

    void setUp() {
        command.banner = [version: version] as HashBannerProvider
    }

    void testVersion() {
        assert "#Shell ${version}" == command.version()
    }
}