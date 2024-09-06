package org.example

class Example {
    String name

    def bax() {}
}

class Example2 {
    @Delegate
    Example example = new Example()

    String foo() {
        print(name)
    }
}

def example2 = new Example2()
example2.name
example2.foo()
example2.bax()


class Mapper {
    def value;

    Mapper(value) {
        this.value = value
    }

    String length() {
        4
    }

    def map(Closure producer) {
        producer.delegate = value
        producer()
    }
}




def mapper  = new Mapper('Hello')
mapper.map {
    print(length())
}
