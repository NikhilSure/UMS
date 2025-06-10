package com.example.UMS.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UMS.model.Publisher;
import com.example.UMS.repository.PublisherRepository;

@Service
public class PublisherService {
    @Autowired
    PublisherRepository publisherRepository;

    public List<Publisher> getAllPublishers() {
        return publisherRepository.findAll();
    }

    public void addPublisher(Publisher publisher) {
        publisherRepository.save(publisher);    
    }

    public Publisher getPublisher(Long publisherId) {
        Optional<Publisher> publisher = publisherRepository.findById(publisherId);
        if (publisher.isPresent()) return publisher.get(); else return null;
    }

    public Publisher updatePublisher(Publisher Publisher) {
        return publisherRepository.save(Publisher) ;
    }
    // public Publisher getNPublisher(Integer n) {
    //     return publisherRepository.fin
    //     // need to implement with pagination
    // }

    public String updateContactInfo(Long publisherId, String contactInfo) {
        Publisher publisher = getPublisher(publisherId); 
        publisher.setContactInfo(contactInfo);
        updatePublisher(publisher);
        return "Successfully updated contact Info!";
    }
}
