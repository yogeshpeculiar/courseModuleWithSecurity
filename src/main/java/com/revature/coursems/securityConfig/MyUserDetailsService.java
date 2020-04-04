
package com.revature.coursems.securityConfig;

import java.util.ArrayList;
import java.util.Optional;

import com.revature.coursems.domain.User;
import com.revature.coursems.repository.CourseRepository;
import com.revature.coursems.service.CourseService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    CourseService courseService;
   
	public UserDetails userDetails;
   
    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        User user=courseService.findUserByName(username); 
		UserBuilder builder = null;
		if (user != null) {
			builder = org.springframework.security.core.userdetails.User.withUsername(username);
			builder.password(user.getPassword());
			builder.roles(user.getRole());
		  }
		 this.userDetails=builder.build();
		return builder.build();
        
    }

}