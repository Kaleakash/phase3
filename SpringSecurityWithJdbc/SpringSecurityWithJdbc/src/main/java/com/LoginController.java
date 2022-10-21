package com;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	@Autowired
	JdbcUserDetailsManager jdbcUserDetalsManager;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping(value = "register-user")
	public String register(@RequestBody Users user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRoles()));
		String password = passwordEncoder.encode(user.getPassword());
		User userInfo =new User(user.getUsername(), password, authorities);
		jdbcUserDetalsManager.createUser(userInfo);
		return "User account created successfully";
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
