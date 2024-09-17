package org.example

Closure download = {String url ->
    url.toURL().text
}

Closure loadFile = {String fileName ->
      //load the file here
}

Closure hash = {s -> s.hashCode()}

Closure compare = {int first, int second ->
    first == second
}

def result = compare(hash(download('http://www.gpars.org')), hash(loadFile('/coolStuff/gpars/website/index.html')))
println "The result of comparison: " + result
