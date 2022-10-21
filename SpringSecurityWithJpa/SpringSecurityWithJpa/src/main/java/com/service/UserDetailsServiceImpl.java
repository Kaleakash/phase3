package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	BCryptPasswordEncoder password;

	@Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);
         
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new MyUserDetails(user);
    }
	
	
	public String saveUserDetails(User user) {
		User uu = userRepository.getUserByUsername(user.getUsername());
		if(uu==null) {
			user.setPassword(password.encode(user.getPassword()));
			userRepository.save(user);
			return "Account created successfully";
		}else {
			return "Acount didn't create";
		}
	}
}
