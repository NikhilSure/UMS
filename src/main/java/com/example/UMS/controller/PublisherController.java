package com.example.UMS.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UMS.model.Publisher;
import com.example.UMS.service.PublisherService;

@RestController
@RequestMapping("/api/publisher")
public class PublisherController {
    @Autowired
    PublisherService publisherService;

    @GetMapping
    public List<Publisher> getPublishers() {
        List<Publisher> publishers = publisherService.getAllPublishers(); 
        return publishers;
    }

    @PostMapping
    public String addPublisher(@RequestBody Publisher publisher) {
        publisherService.addPublisher(publisher);
        return "SuccessFully added";
    }

    @GetMapping("/{id}")
    public Publisher getPublisher(@PathVariable("id")  Long publisherId) {
        Publisher publisher = publisherService.getPublisher(publisherId);
        return publisher;
    }
}
