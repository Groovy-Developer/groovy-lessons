package org.example

def dog = new Dog()
println dog.someMethod("hello")
println dog.test()


println dog.field1
println dog.field2
println dog.field3
println dog.field4

dog.a = 'a'
println dog.a

println dog.metaClass.getAttribute(dog, 'field1')
println dog.metaClass.getAttribute(dog, 'field2')

dog.metaClass.setAttribute(dog, 'a', 'a')
println dog.a