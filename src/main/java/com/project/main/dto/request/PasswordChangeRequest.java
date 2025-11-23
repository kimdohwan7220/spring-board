package com.project.main.dto.request;

public class PasswordChangeRequest {
    private String username;
    private String oldPassword;
    private String newPassword;

    public String getUsername() { return username; }
    public String getOldPassword() { return oldPassword; }
    public String getNewPassword() { return newPassword; }
}
