package org.example

class Book {
    String title
}

Book.metaClass.titleInUpperCase << {title.toUpperCase()}
Book.metaClass.author = "Me"
Book.metaClass.getAuthor = "Me"
Book.metaClass.constructor << {String title -> new Book(title: title + "2")}

Book.metaClass.static.create << {String title -> new Book(title: title + "3")}

def book1 = new Book("test")

assert book1.author == 'Me'
assert book1.titleInUpperCase() == 'TEST2'

assert Book.create("test").titleInUpperCase() == "TEST3"

class Person {
    String name
}

class MortgageLender {
    def borrowMoney() {
        "buy house"
    }
}

def lender = new MortgageLender()
def p = new Person()

class Stuff {
    def invokeMe() { "foo" }

    static invokeMe2() { "foo" }
}

def stf = new Stuff()
