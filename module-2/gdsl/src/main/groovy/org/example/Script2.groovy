package org.example

import static org.example.JavaRunDsl.java

java {
    mainClass = 'Main'
    properties {
        bool_test = true
        str_test = 'Hello'
    }
    arguments {
        pair("key", "value")
        flag("switch")
    }
    classpath'test.jar', 'test2.jar'
}
// java ....
