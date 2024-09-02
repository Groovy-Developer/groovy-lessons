package org.example

@Category(List)
class CollectionCategory {
    void ourForEach(Closure closure) {
        for (def el : this) {
            closure(el)
        }
    }

    void ourMap(Closure clusere) {

    }
}
