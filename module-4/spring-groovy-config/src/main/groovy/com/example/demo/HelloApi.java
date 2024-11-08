package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloApi {
    @Autowired
    private Employee employee;
    @Autowired
    private Company company;
    @Autowired
    private GroovyComponent component;

    @GetMapping("/hello")
    public String hello() {
        return employee.toString() + "\n" + company.printMessage();
    }
}
