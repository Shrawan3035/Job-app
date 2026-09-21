package com.kolekar.spring_boot_rest.controller;

import com.kolekar.spring_boot_rest.model.User;
import com.kolekar.spring_boot_rest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        // Enforce basic roles if client doesn't send one
        if (user.getRole() == null || user.getRole().isEmpty()) {
            user.setRole("USER");
        }
        return service.saveUser(user);
    }

    @GetMapping("/me")
    public ResponseEntity<Object> getMe() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return ResponseEntity.status(401).body("Not authenticated");
        }
        // Return username and role
        return ResponseEntity.ok(new Object() {
            public String username = auth.getName();
            // Principal authorities have "ROLE_" prefix, we just take the first one
            public String role = auth.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");
        });
    }
}
