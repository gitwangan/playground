package com.playground.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Value("${registeredUser.username}")
    private String registeredUserName;
    @Value("${registeredUser.password}")
    private String registeredPassword;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (registeredUserName.equals(username)) {
            return new User(username, registeredPassword, new ArrayList<>());
        } else {
            throw new UsernameNotFoundException("User not registered.");
        }
    }

}
