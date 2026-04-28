package com.expense_tracker.controller;

import com.expense_tracker.dto.LoginRequest;
import com.expense_tracker.dto.LoginResponse;
import com.expense_tracker.model.User;
import com.expense_tracker.service.JwtService;
import com.expense_tracker.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthController(UserService userService, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.findByEmail(request.email).orElseThrow(() -> new RuntimeException("User not Found"));

        if (!passwordEncoder.matches(request.password, user.getPassword())) {
            return ResponseEntity.status(401).body("Invalid Password!");
        }

        String token = jwtService.generateToken(user.getId().toString());

        return ResponseEntity.ok(new LoginResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }
}
