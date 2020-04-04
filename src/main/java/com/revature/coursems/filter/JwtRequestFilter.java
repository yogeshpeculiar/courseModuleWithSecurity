package com.revature.coursems.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.coursems.securityConfig.MyUserDetailsService;
import com.revature.coursems.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtutil;
    
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        final String authorizationheader=request.getHeader("Authorization");
        String username=null;
        String jwt=null;

        if(authorizationheader!=null && authorizationheader.startsWith("Bearer ")){ //takes header stating preceded by Bearer and a space
            jwt=authorizationheader.substring(7);   //to exclude Beared 
            username=jwtutil.extractUsername(jwt);  //getting username fromm the jwt
        }
        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){ //the second condition makes sure that the security context does not already has a AUTHENTICATED user.
           System.out.println("filter is callled");
            UserDetails userDetails=userDetailsService.loadUserByUsername(username);
           if(jwtutil.validateToken(jwt, userDetails)){
               UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(
                   userDetails, null, userDetails.getAuthorities());  //takes principal,credentials and authorities as args
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
           } 
        }
        filterChain.doFilter(request,response);
    }

}