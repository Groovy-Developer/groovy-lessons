package org.example

Closure download = {String url ->
    url.toURL().text
}.asyncFun()

Closure loadFile = {String fileName ->
     //load the file here
}.asyncFun()

Closure hash = {s -> s.hashCode()}.asyncFun()

Closure compare = {int first, int second ->
    first == second
}.asyncFun()

def result = compare(hash(download('http://www.gpars.org')), hash(loadFile('/coolStuff/gpars/website/index.html')))

println 'Allowed to do something else now'
println "The result of comparison: " + result.get()

class DownloadHelper {

    String download(String url) {
        url.toURL().text
    }

    int scanFor(String word, String text) {
        text.findAll(word).size()
    }

    String lower(s) {
        s.toLowerCase()
    }
}

//now we'll make the methods asynchronous
// Methods can be referred to as closures using the .& operator.
//GParsPool.withPool {
//    final DownloadHelper d = new DownloadHelper()
//    Closure download = d.&download.asyncFun()   // notice the .& syntax
//    Closure scanFor = d.&scanFor.asyncFun()                // and here
//    Closure lower = d.&lower.asyncFun()                        // and here
//
//    //asynchronous processing
//    def result = scanFor('groovy', lower(download('http://www.infoq.com')))
//    println 'Doing something else for now'
//    println result.get()
//}