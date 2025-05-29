package com.example.UMS.controller;

import com.example.UMS.model.Book;
import com.example.UMS.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
       
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        System.out.println(book.toString());
        return bookService.addBook(book);
    }

    @PostMapping("/insertBooks")
    public void addBooks(@RequestBody Book[] book) {
        System.out.println(book.toString());
        bookService.saveListBooks(book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
