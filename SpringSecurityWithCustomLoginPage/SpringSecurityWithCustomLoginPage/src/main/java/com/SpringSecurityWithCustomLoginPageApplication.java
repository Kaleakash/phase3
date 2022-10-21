package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityWithCustomLoginPageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityWithCustomLoginPageApplication.class, args);
		System.out.println("Server up!");
	}

}
