
// package com.revature.coursems.securityConfig;

// import java.util.ArrayList;
// import java.util.Optional;

// import com.revature.coursems.repository.CourseRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class MyUserDetailsService implements UserDetailsService {
//     @Autowired
//     CourseRepository courseRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
//         return new User("foo", "foo",new ArrayList<>());
        
//     }

// }