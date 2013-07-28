import groovy.transform.ToString

class Hash {
    PackageHandler packageHandler = new PackageHandler()
    ConfigurationHandler configurationHandler = new ConfigurationHandler()

    void packages(Closure c) {
        def closure = c.clone() as Closure

        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.delegate = packageHandler
        closure.call()
    }

    void config(Closure c) {
        def closure = c.clone() as Closure

        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.delegate = configurationHandler
        closure.call()
    }
}

@ToString
class ConfigurationHandler {
    File components

    void components(String components) {
        this.components = new File(components)
    }
}

@ToString
class PackageHandler {
    List packages = []

    def methodMissing(String name, args) {
        def p = new Package(name: name)
        def closure = args.last().clone() as Closure
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.delegate = p
        closure.call()

        packages << p
    }
}

@ToString(ignoreNulls = true)
class Package {
    String name
    String version
    String url
    Map dependencies
    List main = []
    Repository repository = new Repository()

    void sources(String main) {
        this.main << main
    }

    void sources(String... main) {
        this.main = main
    }

    void name(String name) {
        this.name = name
    }

    void dependencies(Map dependencies) {
        this.dependencies = dependencies
    }

    void version(String version) {
        this.version = version
    }

    void repository(Closure c) {
        def closure = c.clone() as Closure

        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.delegate = repository
        closure.call()
    }
}

@ToString(ignoreNulls = true)
class Repository {
    String type
    String url

    def methodMissing(String name, args) {
        this."$name" = args.last()
    }
}
def h = new Hash()
h.config {
    components 'src/main/hash/components'
}
h.packages {
    jquery {
        version '1.9.1'
        sources 'jquery.min.js'

        repository {
            type 'js'
            url 'http://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js'
        }
    }

    bootstrap {
        sources './docs/assets/css/bootstrap.js', './docs/assets/css/bootstrap.css'
        dependencies jquery: '1.9.1'

        repository {
            type 'git'
            url 'https://github.com/twitter/bootstrap.git'
        }
    }

    'coffee-script' {
        sources 'coffee-script.js'

        repository {
            type 'js'
            url 'http://coffeescript.org/extras/coffee-script.js'
        }
    }
}

println h.configurationHandler
println h.packageHandler