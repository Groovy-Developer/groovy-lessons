package org.example

import sun.reflect.generics.reflectiveObjects.NotImplementedException

/**
 ToDo:
    - Prepare Breakfast [start=, end=]
        + cook salad
        - cook cookies
    -
 */

class ToDoListBuilder extends BuilderSupport {
    int level = 0
    def result = new StringWriter()


    @Override
    protected void setParent(name, Object o1) {
    }

    @Override
    protected Object createNode(name) {
    }

    @Override
    protected Object createNode(name, Object o1) {
        return null
    }

    @Override
    protected Object createNode(name, Map attributes) {
        handle(name, attributes)
    }

    @Override
    protected Object createNode(Object o, Map map, Object o1) {
        return null
    }


    String handle(String name, Map attributes) {
        level++
        level.times { result << "\t"}
        // todo: status of action or task
        result << printParameters(attributes)
        result << "\n"
    }

    def String printParameters(Map attributes) {
        def values = ""
        if(attributes.size() > 0) {
            values += "["
            attributes.each { key, value ->
                values += "${key}= ${value}"
            }
            values += "]"
        }
    }
}

builder = new ToDoListBuilder()
builder.build {
    Prepare_Breakfast(start: '09/09/24', end: '12/09/24') {
        cook_salad(start: '09/09/24', end: '12/09/24', status: 'done')
        cook_cookies(start: '09/09/24', end: '12/09/24')
    }
}

println builder.result
