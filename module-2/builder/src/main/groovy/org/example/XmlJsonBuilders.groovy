package org.example

import groovy.json.JsonBuilder
import groovy.xml.MarkupBuilder
def str = "Hello"
/**
<html>
    <header>
        <script>Console.open()</script>
    </header>
    <body>
        <h1 id="123"><Hello</h1>
        <div>
            <p>test</p>
         </div>
    </body>
</html>
 **/
def builder = new MarkupBuilder()

builder.html {
    header {
        script("$str")
    }
    body {
        h1('Hello')
        div {
            p('test')
        }
    }
}

println builder.toString()

/**
 {
    "first_name": "John",
    "second_name": "Smith",
    "skills": ["Java", "Groovy"],
    "address": {
        "city": "Moscow",
        "country": "Russia"
    }
 }
 */
def jsonBuilder = new JsonBuilder()
def skillsList = ["Java", "Groovy"]
jsonBuilder {
    first_name "John"
    second_name "Smith"

    skills skillsList
    address {
        city "Moscow"
        country "Russia"
    }
}

println jsonBuilder.toString()
