package org.example

import groovy.util.Proxy
import org.codehaus.groovy.runtime.InvokerHelper

class DogProxy extends Proxy{
    @Override
    Object getProperty(String propertyName) {
        try {
            def value = getAdaptee().getProperty(propertyName)
            print("Property=${propertyName}, value=${value}")
            return value
        } catch (MissingPropertyException e) {

        }
    }

    @Override
    Object invokeMethod(String name, Object args) {
        println "Proxy started"
        def result = InvokerHelper.invokeMethod(getAdaptee(), name, args);
        println "Result: ${result}"
        return result
    }
}
