package org.volcra.hash.shell.commands

import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component

@Component
class InstallCommand implements CommandMarker {
    @CliCommand(value = "install", help = "Installs a hash command")
    def install(
        @CliOption(key = [ "name" ], mandatory = true, help = "The command name to install") String name) {
        println "Installing command $name"
    }
}
