package org.example

import groovy.json.JsonSlurper

Map result = new JsonSlurper().parseText(' {\n' +
        '    "first_name": "John",\n' +
        '    "second_name": "Smith",\n' +
        '    "skills": ["Java", "Groovy"],\n' +
        '    "address": {\n' +
        '        "city": "Moscow",\n' +
        '        "country": "Russia"\n' +
        '    }\n' +
        ' }')

println result.first_name
println ((result.address as Map).city)
