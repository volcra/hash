package org.volcra.hash.shell.commands

import org.springframework.beans.factory.annotation.Value
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.stereotype.Component

/**
 * List all packages that has been installed.
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
class BowerListCommand implements CommandMarker {
    /**
     * Components root folder.
     */
    @Value("#{shellProperties['components.dir']}")
    File components

    /**
     * List all installed packages.
     *
     * @return
     */
    @CliCommand(value = 'bower list', help = 'List all installed packages')
    void list() {
        components.eachDir { println it.name }
    }
}
