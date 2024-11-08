package com.example.demo

class Company {
    def name
    def customizer

    def init() {
        customizer.customize(this)
    }

    def printMessage() {
        externalMessage
    }
}
