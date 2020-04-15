
package com.revature.coursems.securityConfig;

import java.util.Optional;

import javax.transaction.Transactional;

import com.revature.coursems.domain.MyUserDetails;
import com.revature.coursems.domain.User;
import com.revature.coursems.repository.CourseRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    CourseRepository courseRepository;
    
    @Transactional
   @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user =courseRepository.findUserByName(username);

      UserBuilder builder = null;
      if (user != null) {
        builder = org.springframework.security.core.userdetails.User.withUsername(username);
        builder.password(user.getPassword());
        builder.roles(user.getRole());
      } else {
        throw new UsernameNotFoundException("User not found.");
      }
  
      return builder.build();
        
        
    }

}