package org.example

import static groovyx.gpars.GParsPool.*

withPool {
    def list = ['123', '345']
    list = list.collectParallel {it+'4'}
    print(list)
}
