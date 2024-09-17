package org.example

import groovyx.gpars.AsyncFun
import groovyx.gpars.GParsPool

class DownloadingSearch {
    @AsyncFun Closure download = { String url ->
        url.toURL().text
    }

    @AsyncFun Closure scanFor = {String word, String text ->
        text.findAll(word).size()
    }

    @AsyncFun Closure lower = {s -> s.toLowerCase()}

    void scan() {
        def result = scanFor('groovy', lower(download('http://www.infoq.com')))  //synchronous processing

        println 'Allowed to do something else now'
        println result.get()
    }

    static void main(String[] args) {
        GParsPool.withPool {
            new DownloadingSearch().scan()
        }
    }
}
