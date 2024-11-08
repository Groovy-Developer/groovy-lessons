package com.example.demo

import groovy.transform.ToString

@ToString
class Employee {
    def firstName
    def lastName
    def address
    Company employer
}
