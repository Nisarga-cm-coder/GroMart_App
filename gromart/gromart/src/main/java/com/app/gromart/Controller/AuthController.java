package com.app.gromart.Controller;

import com.app.gromart.dto.LoginRequest;
import com.app.gromart.dto.SignupRequest;
import com.app.gromart.dto.JwtResponse;
import com.app.gromart.Entity.User;
import com.app.gromart.Repository.UserRepo;
import com.app.gromart.Service.UserService;
import com.app.gromart.Config.JwtUtils;
import com.app.gromart.Service.UserDetailsServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private UserRepo userRepo;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody SignupRequest request) {
        userService.register(request);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

        // Get role from DB
        String role = userRepo.findByUsername(request.getUsername())
                .map(User::getRole)
                .orElse("ROLE_USER");

        String token = jwtUtils.generateToken(userDetails, role);

        return ResponseEntity.ok(new JwtResponse(token, role));
    }
}
