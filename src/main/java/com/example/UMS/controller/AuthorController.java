package com.example.UMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.UMS.model.Author;
import com.example.UMS.service.AuthorService;
import com.example.UMS.service.FileStorageService;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    AuthorService authorService;
    
    @Autowired 
    FileStorageService fileStorageService;

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.getAllAuthors();
    }

    @PostMapping
    public Author addAuthor( @RequestParam String fullname,
            @RequestParam String bio,
            @RequestParam MultipartFile profilePic) {
        String filepath = fileStorageService.storeImage(profilePic);
        Author author = Author.builder().fullname(fullname).bio(bio).profilePicUrl(filepath).build();
        authorService.addAuthor(author);
        return author;
    }
}
