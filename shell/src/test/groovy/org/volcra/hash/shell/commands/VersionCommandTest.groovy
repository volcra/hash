package org.volcra.hash.shell.commands

import groovy.util.GroovyTestCase

class VersionCommandTest extends GroovyTestCase {
    def VersionCommand comand = new VersionCommand

    void setUp() {
        println "set up"
    }

    void testVersion() {
        assert "0.0.1-SNAPSHOT" == commad.version()
    }
}