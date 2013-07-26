package org.volcra.hash.shell.addon.bower

import groovy.io.FileType
import groovy.json.JsonSlurper
import org.apache.commons.io.FileUtils
import org.eclipse.jgit.lib.Ref
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.shell.core.HashColorLogger
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import org.volcra.hash.shell.git.GitRepository

/**
 *
 * @author Emanuelle Gardu&ntilde;o
 */
@Component
class BowerOperationsImpl implements BowerOperations {
    /**
     * Cache the bower response in a map.
     */
    private final static Map CACHE = [:]

    /**
     * Constant to print the command output.
     */
    private static final String BOWER_CMD_OUT = '  bower '

    /**
     * Constant bower.
     */
    private final static String BOWER = 'bower'

    /**
     * Components root folder.
     */
    @Value("#{shellProperties['components.dir']}")
    File components

    /**
     * Bower endpoint.
     */
    @Value("#{bowerProperties['bower.url']}")
    URI endpoint

    /**
     * Rest Template to call Bower.
     */
    @Autowired
    RestTemplate restTemplate

    /**
     * Utility class to log to the console with colors.
     */
    @Autowired
    HashColorLogger colorLogger

    /**
     * Calls the bower registry to retrieve all available packages.
     *
     * @return JSON response as String
     */
    private bowerComponentList() {
        if (CACHE.containsKey(BOWER)) CACHE[BOWER]
        else CACHE[BOWER] = restTemplate.getForObject endpoint, String
    }

    /**
     * Parses the bower JSON response with a {@code JsonSlurper}.
     *
     * @return JSON
     */
    private jsonBowerList() {
        new JsonSlurper().parseText bowerComponentList()
    }

    @Override
    def find(String name) {
        jsonBowerList().find { it.name == name }
    }

    @Override
    Iterable find(Closure filter) {
        jsonBowerList().findAll filter
    }

    @Override
    void info(String name) {
        def pkg = find name

        if (pkg) {
            def gitRepository = new GitRepository(pkg.name as String, pkg.website as String, new File(".hash/$name"))

            colorLogger.cyan name printNewline()
            println '\nVersions: '

            gitRepository.tagList().each { Ref ref ->
                println "- ${ref.name.substring(ref.name.lastIndexOf('/') + 1)}"
            }
        } else
            colorLogger.cyan name yellow ' was not found' printNewline()
    }

    @Override
    void install(String name, String version) {
        def pkg = find name

        if (pkg) {
            colorLogger.log BOWER_CMD_OUT cyan 'cloning ' log pkg.website printNewline()
            def git = new GitRepository(name, pkg.website as String, new File(".hash/$name"))
            def tag = version == null || version == 'latest' ? git.tagList().last().name : version

            colorLogger.log BOWER_CMD_OUT cyan 'fetching ' log name printNewline()
            git.fetch()
            colorLogger.log BOWER_CMD_OUT cyan 'checking out ' log "$name#$tag" printNewline()
            git.checkout tag

            def repository = git.repository.directory.parentFile
            def component = new File(components, repository.name)

            component.mkdirs()

            def slurper = new JsonSlurper()
            repository.eachFileMatch(FileType.FILES, ~/(bower|component)\.json/) {
                def json = slurper.parseText it.text

                if (json.dependencies)
                    json.dependencies.each {
                        println "${it.key}->${it.value}"
                        install it.key as String, it.value as String
                    }
            }

            colorLogger.log BOWER_CMD_OUT cyan 'installing ' log "$name#$git.repository.fullBranch" printNewline()
            FileUtils.copyDirectory repository, component
            FileUtils.deleteDirectory new File(component, '.git')
            colorLogger.cyan name green ' installed' printNewline()
        } else
            colorLogger.cyan name yellow ' was not found' printNewline()
    }

    @Override
    void list() {
        components.eachDir { println it.name }
    }

    @Override
    void lookup(String name) {
        def pkg = find name

        if (pkg) colorLogger.cyan "$pkg.name " log pkg.website printNewline()
        else colorLogger.cyan name yellow ' was not found' printNewline()
    }

    @Override
    void search(String name) {
        def matches = find { it.name.contains name }

        if (matches) {
            println 'Search results:\n'

            matches.each { colorLogger.cyan "    $it.name " log "$it.website" printNewline() }
        } else
            colorLogger.yellow 'No results' printNewline()
    }

    @Override
    void uninstall(String name) {
        def pkg = new File(components, name)

        if (pkg.exists()) {
            pkg.deleteDir()

            colorLogger.cyan name green ' deleted' printNewline()
        } else
            colorLogger.cyan name yellow ' is not installed. You may use bower list to show all installed packages'
    }
}
