package org.example

class App {
    static void main(String[] args) {
        def binding = new Binding()
        binding.setProperty('name', 'Petr')
        def shell = new GroovyShell(binding)
        shell.evaluate(new File('Script1.groovy'))
    }
}
