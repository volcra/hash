/*
 * Copyright 2013 Volcra
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.volcra.hash.shell.commands

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.shell.core.CommandMarker
import org.springframework.shell.core.annotation.CliCommand
import org.springframework.shell.core.annotation.CliOption
import org.springframework.stereotype.Component
import org.volcra.hash.shell.bower.BowerRegistry

/**
 * Search command.
 *
 * <p>Searches the registry for packages and prints the result.</p>
 * <p>Command syntax {@code search [name | --name name] [--contains]}
 * <p>Some example commands are:</p>
 * <p>Contains search with {@code --name} option<br>{@code search --name jquery --contains}</p>
 * <p>Contains search with package name as default option<br>{@code search jquery --contains}</p>
 * <p>Exact match search only<br>{@code search jquery}</p>
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
class SearchCommand implements CommandMarker {
    @Autowired
    BowerRegistry bowerRegistry

    /**
     * Searches for packages in the registry.
     *
     * <p>By default the search is for an exact match of the package name in the registry. Use the contains flag to
     * change the search mode.</p>
     * <pre>
     * search jquery
     * jquery               https://github.com/components/jquery
     * Found 1 results
     * </pre>
     * Where as
     * <pre>
     * search jquery --contains
     * ...
     * chai-jquery          https://github.com/chaijs/chai-jquery
     * buster-jquery-ass... https://github.com/blittle/buster-jquery-assertions
     * bootstrap-jquery-... https://github.com/sunliwen/bootstrap-jquery-tags
     * bolster.jquerypp     https://github.com/bolster/jquerypp
     * bacon-jquery-bind... https://github.com/raimohanska/bacon-jquery-bindings
     * Found 311 results
     * </pre>
     * Output was truncated for abbreviation.
     *
     * @param name the name of the package
     * @param contains whether to do a contains search or not
     * @return a message showing the number of findings
     */
    @CliCommand(value = 'search', help = 'Searches for packages in the repository')
    String execute(@CliOption(key = ['name', ''], mandatory = true, help = 'The name of the package') String name,
                   @CliOption(key = 'contains', help = 'Whether to do a contains search or not',
                           specifiedDefaultValue = 'true') Boolean contains) {
        def matches = bowerRegistry.find contains ? { it.name.contains name } : { it.name == name }

        matches.each {
            println sprintf('%-20.20s %s', it.name.size() > 20 ? "${it.name[0..16]}..." : it.name, it.website)
        }

        "Found ${matches.size()} results"
    }
}
