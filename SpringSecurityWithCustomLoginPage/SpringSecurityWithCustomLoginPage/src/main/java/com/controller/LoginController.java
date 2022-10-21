package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

	@RequestMapping("/login")
	public String openLoginPage() {
		return "login";
	}
	
	@RequestMapping("/home")
	public String home() {
		System.out.println("I Came here success");
		return "home";
	}
	
	@RequestMapping("/failure")
	public String failure() {
		System.out.println("I Came here failure");
		return "failure";
	}
}
