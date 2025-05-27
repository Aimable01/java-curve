package org.example.restful.controller;

import org.example.restful.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HomeController {

    @GetMapping("/")
    public ResponseEntity<ApiResponse<Map<String, Object>>> home() {
        Map<String, Object> info = new HashMap<>();
        info.put("name", "Secure Notes API");
        info.put("version", "1.0.0");
        info.put("description", "A RESTful API for managing secure notes");

        Map<String, String> endpoints = new HashMap<>();
        endpoints.put("POST /auth/register", "Register a new user");
        endpoints.put("POST /auth/login", "Login and get JWT token");
        endpoints.put("GET /posts", "List authenticated user's posts (auth required)");
        endpoints.put("POST /posts", "Create a new post (auth required)");
        endpoints.put("GET /posts/{id}", "Get a specific posts (auth required)");
        endpoints.put("PUT /posts/{id}", "Update a specific post (auth required)");
        endpoints.put("DELETE /posts/{id}", "Delete a specific post (auth required)");

        info.put("endpoints", endpoints);

        return ResponseEntity.ok(ApiResponse.success("Welcome to Nest Fit API", info));
    }
}
