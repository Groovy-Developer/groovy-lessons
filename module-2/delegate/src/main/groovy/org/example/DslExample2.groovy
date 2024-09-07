package org.example

import groovy.transform.ToString

//  user code

def result = email {
    from "example@example.com"
    to = "to@example.com"
    subject = 'subject'
    body =  'Hello World!'
}
print result

// library code
@ToString
class EmailSpec {
    def from(String arg) {}
    String to
    String subject
    String body
}

def email(@DelegatesTo(EmailSpec) Closure cl) {
    def email = new EmailSpec()
    /*
        delegate
        owner - владелец, место откуда вызывается кложур (по-умолчанию все методы и св-ва ссылаются на него)
        this
     */
    def rehydrate = cl.rehydrate(email, this, this)
    //def rehydrate = cl.rehydrate(email, email, this)
    // откуда брать св-ва и методы?
    rehydrate.resolveStrategy = Closure.DELEGATE_ONLY
    rehydrate()
    email
}
