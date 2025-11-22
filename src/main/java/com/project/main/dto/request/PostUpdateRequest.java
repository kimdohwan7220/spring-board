package com.project.main.dto.request;

import jakarta.validation.constraints.NotBlank;

public class PostUpdateRequest {

    @NotBlank
    private String title;

    @NotBlank
    private String content;

    public PostUpdateRequest() {
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}