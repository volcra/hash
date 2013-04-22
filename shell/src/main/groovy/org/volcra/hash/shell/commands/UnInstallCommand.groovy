package org.volcra.hash.shell.commands

import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption

import org.springframework.stereotype.Component

@Component
class UnInstallCommand implements CommandMarker {
    @CliCommand(value = "uninstall", help = "Uninstalls a hash command")
    def uninstall(
            @CliOption(key = [ "name" ], mandatory = true, help = "The command name to uninstall") String name) {
        println "Uninstalling command $name"
    }
}
