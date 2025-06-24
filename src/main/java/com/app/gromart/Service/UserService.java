package com.app.gromart.Service;

import com.app.gromart.Entity.User;
import com.app.gromart.Repository.UserRepo;
import com.app.gromart.dto.SignupRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void register(SignupRequest request) {
        if (userRepo.existsByUsername(request.getUsername())) {
            throw new RuntimeException("Username already exists");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());

        // Set role based on request, default to ROLE_USER
        String role = request.getRole();
        if (role == null || (!role.equals("ROLE_ADMIN") && !role.equals("ROLE_USER"))) {
            role = "ROLE_USER";
        }
        user.setRole(role);

        userRepo.save(user);
    }
}
