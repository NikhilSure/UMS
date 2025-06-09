package com.example.UMS.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UMS.model.Author;
import com.example.UMS.repository.AuthorRepository;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public void addAuthor(Author author) {
        authorRepository.save(author);    
    }

    public Author getAuthor(Long authorId) {
        Optional<Author> author = authorRepository.findById(authorId);
        if (author.isPresent()) return author.get(); else return null;
    }

    public Author updateAuthor(Author Author) {
        return authorRepository.save(Author) ;
    }
    // public Author getNAuthor(Integer n) {
    //     return authorRepository.fin
    //     // need to implement with pagination
    // }

    public String updateProfile(Long authorId, String profilePicUrl) {
        Author author = getAuthor(authorId); 
        author.setProfilePicUrl(profilePicUrl);
        updateAuthor(author);
        return "Successfully updated profile picture!";
    }
}
