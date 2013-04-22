package org.volcra.hash.shell.commands

class VersionCommandTest extends GroovyTestCase {
    def command = new VersionCommand()

    void setUp() {
        println "set up"
    }

    void testVersion() {
        assert "0.0.1-SNAPSHOT" == command.version()
    }
}