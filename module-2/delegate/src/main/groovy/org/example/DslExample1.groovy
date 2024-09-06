package org.example

def show = {item ->
    print item
}

square_root = {it -> return Math.sqrt(it)}

def please(action) {
    [the: {func ->
            [of: {n -> action(func(n))}]
        }
    ]
}

def object = please(show)
def object2 = object.the(square_root)
object2.of(100)

//please show the square_root of 100


//n = of(100)
//result = square_root(n)
//result = the(result)
//please(show)
//
//please(show)  square_root() of(100)
//please show the square_root of 100
//please show the exp of 100

