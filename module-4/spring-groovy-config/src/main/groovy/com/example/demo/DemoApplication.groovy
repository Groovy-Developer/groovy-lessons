package com.example.demo

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class DemoApplication {

	static void main(String[] args) {
		SpringApplication app = new SpringApplication(DemoApplication)
		def sources = ["classpath:companies.groovy"]
		app.setSources(sources.toSet())
		app.run(args)
	}

}
