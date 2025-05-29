package com.example.UMS.service;

import com.example.UMS.model.Book;
import com.example.UMS.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public void saveListBooks(Book[] books) {
        bookRepository.saveAll(Arrays.asList(books));
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
