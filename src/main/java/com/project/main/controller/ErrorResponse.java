package com.project.main.controller;

public record ErrorResponse(
        int status,
        String message
) {}
