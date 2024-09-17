package org.example

import groovy.transform.ToString
import groovy.transform.TupleConstructor
import groovyx.gpars.dataflow.*
import groovyx.gpars.extra166y.Ops

// Summarize numbers concurrently.
GParsPool.withPool {
    final AtomicInteger result = new AtomicInteger(0)
    [1, 2, 3, 4, 5].eachParallel{result.addAndGet(it)}

    assert 15 == result
}

// Multiply numbers asynchronously.
GParsPool.withPool {
    final List result = [1, 2, 3, 4, 5].collectParallel{it * 2}

    assert ([2, 4, 6, 8, 10].equals(result))
}

GParsPool.withPool() {
    /**
     * The callAsync() method is an asynchronous variant of the default call() method to invoke a closure.
     * It will return a Future for the result value.
     */
    assert 6 == {it * 2}.call(3)
    assert 6 == {it * 2}.callAsync(3).get()
}

GParsPool.withPool() {
    Closure longLastingCalculation = {calculate()}
    Closure fastCalculation = longLastingCalculation.async()  //create a new closure, which starts the original closure on a thread pool

    Future result= fastCalculation()                           //returns almost immediately

    //do stuff while calculation performs ...
    println result.get()
}

Closure fib = {n -> n > 1 ? call(n - 1) + call(n - 2) : n}

Closure fib2
fib2 = {n -> n > 1 ? fib2(n - 1) + fib2(n - 2) : n}.gmemoize()
//fib = {n -> n > 1 ? fib(n - 1) + fib(n - 2) : n}.memoizeAtMost(2)

GParsPool.withPool {

    assert 15 == [1, 2, 3, 4, 5].parallelArray.reduce({ a, b -> a + b } as Ops.Reducer, 0)
    //summarize

    assert 55 == [1, 2, 3, 4, 5].parallelArray.withMapping({ it**2 } as Ops.Op).reduce({ a, b -> a + b } as Ops.Reducer, 0)
    //summarize squares

    assert 20 == [1, 2, 3, 4, 5].parallelArray.withFilter({ it % 2 == 0 } as Ops.Predicate)
    //summarize squares of even numbers
            .withMapping({ it**2 } as Ops.Op)
            .reduce({ a, b -> a + b } as Ops.Reducer, 0)

    assert 'aa:bb:cc:dd:ee' == 'abcde'.parallelArray
    //concatenate duplicated characters with separator
            .withMapping({ it * 2 } as Ops.Op)
            .reduce({ a, b -> "$a:$b" } as Ops.Reducer, "")
}

GParsExecutorsPool.withPool { ExecutorService executorService ->
    executorService << {println 'Inside parallel task'}
}


import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import java.util.concurrent.atomic.AtomicInteger

@TupleConstructor @ToString
class PricedCar implements Cloneable {        // either Clonable or Immutable
    String model
    String color
    Double price

    // declare a way to resolve comparison logic
    boolean equals(final o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        final PricedCar pricedCar = (PricedCar) o

        if (color != pricedCar.color) return false
        if (model != pricedCar.model) return false

        return true
    }

    int hashCode() {
        int result
        result = (model != null ? model.hashCode() : 0)
        result = 31 * result + (color != null ? color.hashCode() : 0)
        return result
    }

    @Override
    protected Object clone() {
        return super.clone()
    }
}

def cars = [new PricedCar('F550', 'blue', 2342.223),
            new PricedCar('F550', 'red', 234.234),
            new PricedCar('Da', 'white', 2222.2),
            new PricedCar('Da', 'white', 1111.1)]

withPool {
    //Combine by model
    def result =
            cars.parallel.map {
                [it.model, it]
            }.combine(new PricedCar('', 'N/A', 0.0)) {sum, value ->
                sum.model = value.model
                sum.price += value.price
                sum
            }.values()

    println result
}

withPool {
    urls = ['http://www.groovy.com', 'http://www.example.com']
    println 'Number of occurrences of the word GROOVY today: ' + urls.parallel
            .map {it.toURL().text.toUpperCase()}
            .filter {it.contains('GROOVY')}
            .map{it.split()}
            .map{it.findAll{word -> word.contains 'GROOVY'}.size()}
            .sum()
}


import static groovyx.gpars.GParsPool.withPool
import static groovyx.gpars.dataflow.Dataflow.task

//final def x = new DataflowVariable()
//final def y = new DataflowVariable()
//final def z = new DataflowVariable()

final def df = new Dataflows()
task {
    df.z = df.x + df.y
}
task {
    df.x = 10
}
task {
    df.y = 5
}
println "Result: ${df.z}"

def words = ['Groovy', 'fantastic', 'concurrency', 'fun', 'enjoy', 'safe', 'GPars', 'data', 'flow']
final def buffer = new DataflowQueue()

task {
    for (word in words) {
        buffer << word.toUpperCase()  //add to the buffer
    }
}

task {
    while(true) println buffer.val  //read from the buffer in a loop
}

DataflowWriteChannel broadcastStream = new DataflowBroadcast()
DataflowReadChannel stream1 = broadcastStream.createReadChannel()
DataflowReadChannel stream2 = broadcastStream.createReadChannel()

broadcastStream << 'Message1'
broadcastStream << 'Message2'
broadcastStream << 'Message3'

assert stream1.val == stream2.val
assert stream1.val == stream2.val
assert stream1.val == stream2.val

def a = new DataflowVariable()

a >> {println "The variable has just been bound to $it"}

a.whenBound {println "Just to confirm that the variable has been really set to $it"}

a << 3