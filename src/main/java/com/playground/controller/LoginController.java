package com.playground.controller;

import com.playground.util.JwtUtil;
import com.playground.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(user.getUsername(), user.getPassword());
        Authentication authenticationResponse = this.authenticationManager.authenticate(authenticationRequest);
        return new ResponseEntity<>(jwtUtil.generateJwt(user.getUsername()), HttpStatus.OK);
    }
}
