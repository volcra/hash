package org.volcra.hash.shell.commands

import org.volcra.hash.shell.support.HashBannerProvider


class VersionCommandTest extends GroovyTestCase {
    def command = new VersionCommand()

    void setUp() {
        command.banner = [version: "0.0.1-SNAPSHOT"] as HashBannerProvider
    }

    void testVersion() {
        assert "#Shell 0.0.1-SNAPSHOT" == command.version()
    }
}