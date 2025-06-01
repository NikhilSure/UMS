package com.example.UMS.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    
    @GetMapping
    public String getmappString() {
        return "Welcome to proj";
    }

}
