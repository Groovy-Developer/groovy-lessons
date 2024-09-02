package org.example

class Interception implements GroovyInterceptable {
    def test() {
        print("Test method")
        "Test method"
    }

    def test2() {
        print("Test2 method")
        "Test2 method"
    }

    @Override
    Object invokeMethod(String name, Object args) {
        println 'invoke method start'
        def result = null
        if (name == "test") {
            result =  test()
        }
        println "Result: ${result}"
        return result
    }
}
