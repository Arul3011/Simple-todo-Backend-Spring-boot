package com.example.my_app.controller;

import org.springframework.web.bind.annotation.*;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import com.example.my_app.util.JwtUtil;
import com.example.my_app.models.User;
import com.example.my_app.repositories.UserRepo;
import com.example.my_app.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserRepo userRepo;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @GetMapping("/test")
    public ResponseEntity<String> testRout(){
        return ResponseEntity.ok("testing scucessfull");
    }
    @PostMapping("/reg")
    public ResponseEntity<String> regUser(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
if(!body.containsKey("email") || !body.containsKey("password")){
    return ResponseEntity.badRequest().body("email or password missing");
    }
        if (userRepo.findByEmail(email).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }

        User user = User.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();

        userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        String password = body.get("password");
if(!body.containsKey("email") || !body.containsKey("password")){
    return ResponseEntity.badRequest().body("email or password missing");
    }
        var userOpt = userRepo.findByEmail(email);
        if (userOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not registered");
        }

        User user = userOpt.get();
        if (!passwordEncoder.matches(password, user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Password does not match");
        }

        String token = jwtUtil.generateToken(email);
        return ResponseEntity.ok(Map.of("token", token));
    }
}
