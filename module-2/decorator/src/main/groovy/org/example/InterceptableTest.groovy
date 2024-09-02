package org.example

class InterceptableTest {
    static void main(String[] args) {
        Proxy dogProxy = new DogProxy()
        def dog = new Dog()
        dogProxy.wrap(dog)
        dogProxy.getField2()
        dogProxy.field1

        use(CollectionCategory, NumberCategory) {
            [1, 2, 3, 4].ourForEach {el -> print(el)}
            println 10.toMeters()
        }

    }
}
