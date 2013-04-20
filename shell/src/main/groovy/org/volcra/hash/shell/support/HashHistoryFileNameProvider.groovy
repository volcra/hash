package org.volcra.hash.shell.support

import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.shell.plugin.HistoryFileNameProvider
import org.springframework.stereotype.Component

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class HashHistoryFileNameProvider implements HistoryFileNameProvider {

    String getHistoryFileName() {
        "hash-shell.log"
    }

    String name() {
        "hash shell file name provider"
    }
}
