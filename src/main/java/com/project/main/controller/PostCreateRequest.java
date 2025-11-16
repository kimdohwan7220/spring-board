package com.project.main.controller;
import jakarta.validation.constraints.NotBlank;

public record PostCreateRequest(
        @NotBlank String title,
        @NotBlank String content,
        @NotBlank String writer
) {}