package org.example

//  user code

def result = email {
    from "example@example.com"
    to = "example@example.com"
    subject = 'example@example.com'
    body =  'example@example.com'
}
print result

// library code

class EmailSpec {
    def from(String arg) {}
    String to
    String subject
    String body
}

def email(@DelegatesTo(EmailSpec) Closure cl) {
    def email = new EmailSpec()
    def rehydrate = cl.rehydrate(email, this, this)
    rehydrate()
    email
}
