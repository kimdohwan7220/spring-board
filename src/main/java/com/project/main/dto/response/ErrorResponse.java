package com.project.main.dto.response;

public record ErrorResponse(
        int status,
        String message
) {}
