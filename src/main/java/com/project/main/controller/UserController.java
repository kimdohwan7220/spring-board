package com.project.main.controller;

import com.project.main.dto.request.PasswordChangeRequest;
import com.project.main.dto.request.UsernameChangeRequest;
import com.project.main.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // 프로필 이미지 업로드
    @PostMapping("/profile-image")
    public ResponseEntity<String> uploadProfile(
            @RequestParam String username,
            @RequestParam MultipartFile file
    ) {
        String url = service.uploadProfile(username, file);
        return ResponseEntity.ok(url);
    }

    // 비밀번호 변경
    @PutMapping("/password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest req) {
        service.changePassword(req);
        return ResponseEntity.ok("비밀번호 변경 완료!");
    }

    // 유저 정보 조회
    @GetMapping
    public ResponseEntity<?> getUser(@RequestParam String username) {
        return ResponseEntity.ok(service.findUser(username));
    }

    @PutMapping("/username")
    public ResponseEntity<String> changeUsername(@RequestBody UsernameChangeRequest req) {
        service.changeUsername(req);
        return ResponseEntity.ok("닉네임 변경 완료");
    }
}
