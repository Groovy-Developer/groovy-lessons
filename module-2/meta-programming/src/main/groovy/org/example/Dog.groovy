package org.example

class Dog implements GroovyObject {
    static def s1

    static def sTest() {

    }

    static def $static_methodMissing(String name, Object args) {

    }

    @Override
    Object invokeMethod(String name, Object args) {
        return "called invokeMethod ${name} ${args}"
    }

    def field1 = 'ha'
    def field2 = 'ho'
    def field4 = 'hu'

    def getField2() {
        'getField2'
    }

    @Override
    Object getProperty(String propertyName) {
        if( propertyName != 'field3') {
            return metaClass.getProperty(this, propertyName)
        } else {
            return 'field3'
        }
    }

    def a

    @Override
    void setProperty(String propertyName, Object newValue) {
        this.@"${propertyName}" = 'b'
    }

    def test() {
        return "method exists"
    }
}
