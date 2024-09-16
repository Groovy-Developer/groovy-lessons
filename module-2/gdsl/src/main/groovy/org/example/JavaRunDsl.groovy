package org.example

class JavaRunDsl {
    static void java(@DelegatesTo(value = JavaDsl) Closure closure) {
        def javaDsl = new JavaDsl()
        closure = closure.rehydrate(javaDsl, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
        javaDsl.run()
    }
}

class JavaDsl {
    private String mainClass
    private String[] cp
    private ArgumentsDsl arguments
    private Map properties = [:]

    def classpath(String... args) {
        cp = args
    }

    def arguments(@DelegatesTo(value=ArgumentsDsl) Closure closure) {
        arguments = new ArgumentsDsl()
        closure = closure.rehydrate(arguments, this, this)
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure.call()
    }

    def properties(Closure closure) {
        properties.with closure
    }

    def run() {
        println "java -cp ${cp.join(":")} ${mainClass} ${arguments.pairs.join(',')} ${arguments.flag}"
    }
}

class ArgumentsDsl {
    String[] pairs
    String flag

    def pair(String... args) {
        pairs = args
    }

    def flag(String arg) {
        flag = arg
    }
}
