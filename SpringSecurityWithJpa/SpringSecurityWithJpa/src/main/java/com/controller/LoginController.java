package com.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.User;
import com.service.UserDetailsServiceImpl;

@RestController
public class LoginController {

	@Autowired
	UserDetailsServiceImpl userDetailsServieImpl;
	
	@PostMapping(value = "register-user")
	public String register(@RequestBody User user) {
		return userDetailsServieImpl.saveUserDetails(user);
	}
	
	@GetMapping(value="admin")
	public String admin(Authentication auth) {
		String role = auth.getAuthorities().stream().findAny().get().getAuthority();
		return "Welcome admin Page "+auth.getName()+" has role is "+role;
		
	}
	
	@GetMapping(value="user")
	public String user(Authentication auth) {
		String role = auth.getAuthorities().stream().findAny().get().getAuthority();
		return "Welcome user Page "+auth.getName()+" has role is "+role;
	}
	
	@GetMapping(value="/")
	public String anyone() {
		return "Welcome to everyone";
	}
}
