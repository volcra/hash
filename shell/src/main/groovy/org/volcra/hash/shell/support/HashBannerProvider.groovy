package org.volcra.hash.shell.support

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.shell.plugin.BannerProvider
import org.springframework.stereotype.Component

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class HashBannerProvider implements BannerProvider {

    String getBanner() {
        """\
-------------------------------------------------------------------------------
                                _       __ _          _ _
                 /\\  /\\__ _ ___| |__   / _\\ |__   ___| | |
                / /_/ / _` / __| '_ \\  \\ \\| '_ \\ / _ \\ | |
               / __  / (_| \\__ \\ | | | _\\ \\ | | |  __/ | |
               \\/ /_/ \\__,_|___/_| |_| \\__/_| |_|\\___|_|_|

-------------------------------------------------------------------------------
"""
    }

    String getVersion() {
        "0.0.1-SNAPSHOT"
    }

    String getWelcomeMessage() {
        "Welcome to ${name()} ${getVersion()}.\nFor assistance press or type \"help\" then hit ENTER."
    }

    String name() {
        "#Shell"
    }
}