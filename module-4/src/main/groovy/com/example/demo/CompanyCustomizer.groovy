package com.example.demo

import org.springframework.scripting.groovy.GroovyObjectCustomizer

class CompanyCustomizer implements GroovyObjectCustomizer {
    @Override
    void customize(GroovyObject goo) {
        goo.metaClass.newMethod = { 'I am new method!' }
        goo.metaClass.externalMessage = 'External message'
    }
}
