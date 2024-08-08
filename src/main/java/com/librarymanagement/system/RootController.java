package com.librarymanagement.system;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RootController {

    @GetMapping("/")
    public String home() {
        return "Welcome to the Library Management System!";
    }

    @GetMapping("/api")
    public String apiHome() {
        return "Welcome to the Library Management API!";
    }
}