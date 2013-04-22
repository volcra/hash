package org.volcra.hash.shell.commands

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.stereotype.Component
import org.volcra.hash.shell.support.HashBannerProvider

@Component
class VersionCommand implements CommandMarker {
    @Autowired
    HashBannerProvider banner

    @CliCommand("version")
    def version() {
        "${banner.name()} ${banner.getVersion()}"
    }
}