package com.project.main.controller;

import com.project.main.domain.User;
import com.project.main.dto.request.LoginRequest;
import com.project.main.dto.request.RegisterRequest;
import com.project.main.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService service;

    public AuthController(UserService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
        service.register(req.getUsername(), req.getPassword());
        return ResponseEntity.ok("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest req) {
        service.login(req.getUsername(), req.getPassword());
        return ResponseEntity.ok("로그인 성공");
    }

    @GetMapping("/user")
    public User getUser(@RequestParam String username) {
        return service.findUser(username);
    }
}
