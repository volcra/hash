package org.volcra.hash.shell.support

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.shell.plugin.PromptProvider
import org.springframework.stereotype.Component

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class HashPromptProvider implements PromptProvider {

    String getPrompt() {
        "#>"
    }

    String name() {
        "# prompt provider"
    }
}
