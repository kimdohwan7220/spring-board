package com.project.main.service;

import com.project.main.domain.User;
import com.project.main.dto.request.PasswordChangeRequest;
import com.project.main.dto.request.UsernameChangeRequest;
import com.project.main.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public void register(String username, String password) {
        if (repository.findByUsername(username).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 사용자입니다.");
        }
        User user = new User(username, password);
        repository.save(user);
    }

    public User login(String username, String password) {
        return repository.findByUsername(username)
                .filter(u -> u.getPassword().equals(password))
                .orElseThrow(() -> new IllegalArgumentException("아이디 또는 비밀번호가 잘못되었습니다."));
    }

    // ⭐ MyPage.jsx에서 사용
    public User findUser(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
    }

    public String uploadProfile(String username, MultipartFile file) {
        User user = findUser(username);

        try {
            String fileName = username + "_" + file.getOriginalFilename();
            Path uploadPath = Path.of("uploads/profile/" + fileName);

            Files.createDirectories(uploadPath.getParent());
            Files.write(uploadPath, file.getBytes());

            // ⭐ DB에는 URL 경로만 저장
            String fileUrl = "/uploads/profile/" + fileName;
            user.setProfileImage(fileUrl);
            repository.save(user);

            return fileUrl;
        } catch (Exception e) {
            throw new RuntimeException("프로필 업로드 실패", e);
        }
    }

    public void changePassword(PasswordChangeRequest req) {
        User user = findUser(req.getUsername());

        if (!user.getPassword().equals(req.getOldPassword())) {
            throw new IllegalArgumentException("기존 비밀번호가 일치하지 않습니다.");
        }

        user.setPassword(req.getNewPassword());
        repository.save(user);
    }

    public void changeUsername(UsernameChangeRequest req) {
        User user = repository.findByUsername(req.getOldUsername())
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 중복 닉네임 체크
        if (repository.findByUsername(req.getNewUsername()).isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 닉네임입니다.");
        }

        user.setUsername(req.getNewUsername());
        repository.save(user);
    }
}
