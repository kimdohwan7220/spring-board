package com.project.main.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String profileImage;

    protected User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getProfileImage() { return profileImage; }

    public void setPassword(String newPw) { this.password = newPw; }
    public void setProfileImage(String path) { this.profileImage = path; }
    public void setUsername(String username) { this.username = username; }
}
