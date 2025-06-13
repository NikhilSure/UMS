package com.example.UMS.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UMS.dto.BookDTO;
import com.example.UMS.dto.CreateBookDTO;
import com.example.UMS.model.Author;
import com.example.UMS.model.Book;
import com.example.UMS.model.BookImage;
import com.example.UMS.model.Category;
import com.example.UMS.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookService {
      @Autowired
      BookRepository bookRepository;

      @Autowired
      BookImageService bookImageService;

      @Autowired
      AuthorService authorService;

      @Autowired
      PublisherService publisherService;

      @Autowired
      CategoryService categoryService;

        public BookDTO toDto(Book book) {
            if(book == null) return null ;

            return BookDTO.builder()
                .id(book.getBookId())
                .title(book.getTitle())
                .price(book.getPrice())
                .isbn(book.getIsbn())
                .description(book.getDescription())
                .publisherName(book.getPublisher().getName())
                .publishedDate(book.getPublishedDate())
                .categories(book.getCategories().stream().map(category -> categoryService.toDto(category)).collect(Collectors.toList()))
                .images(book.getImages().stream().map(image -> bookImageService.toDto(image)).collect(Collectors.toList()))
                .authorNames(book.getAuthors().stream().map(Author::getFullname).collect(Collectors.toList())).build();
        }

        public Book toEntity(CreateBookDTO createBookDTO) {
            if (createBookDTO == null) return null;

            return Book.builder()
                .title(createBookDTO.getTitle())
                .price(createBookDTO.getPrice())
                .isbn(createBookDTO.getIsbn())
                .publisher(publisherService.getPublisher(createBookDTO.getPublisherId()))
                .description(createBookDTO.getDescription())
                .categories(createBookDTO.getCategoryIds().stream().map(categoryService::getCategory).collect(Collectors.toSet()))
                .images(createBookDTO.getImagesIds().stream().map(id -> bookImageService.getBookImage(id)).collect(Collectors.toList()))
                .authors(createBookDTO.getAuthorIds().stream().map(id -> authorService.getAuthor(id)).collect(Collectors.toSet()))
                .publishedDate(createBookDTO.getPublishedDate())
                .build();
        }

        public BookDTO addBook(CreateBookDTO createBookDTO) {
            Book book = toEntity(createBookDTO);
            return toDto(bookRepository.save(book));
        }

        public BookDTO getBook(Long id) {
            Optional<Book> book = bookRepository.findById(id);
            if (!book.isPresent()) return null;
            return toDto(book.get());
        }

        public List<BookDTO> getAllBooks() {
            List<Book> books = bookRepository.findAll();

            return books.stream()
                .map(this::toDto)
                    .collect(Collectors.toList());
        }


        public BookDTO updateExistingBook(Long id,
        CreateBookDTO 
        createBookDTO ) {
            Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found with ID: " + id));

            // Update only non-null fields
            if (createBookDTO.getTitle() != null)
                existing.setTitle(createBookDTO.getTitle());

            if (createBookDTO.getPrice() != null)
                existing.setPrice(createBookDTO.getPrice());

            if (createBookDTO.getIsbn() != null)
                existing.setIsbn(createBookDTO.getIsbn());

            if (createBookDTO.getPublisherId() != null)
                existing.setPublisher(publisherService.getPublisher(createBookDTO.getPublisherId()));

            if (createBookDTO.getDescription() != null)
                existing.setDescription(createBookDTO.getDescription());

            if (createBookDTO.getPublishedDate() != null)
                existing.setPublishedDate(createBookDTO.getPublishedDate());

            // Add new images (preserving existing ones)
            if (createBookDTO.getImagesIds() != null && !createBookDTO.getImagesIds().isEmpty()) {
                List<BookImage> newImages = createBookDTO.getImagesIds().stream()
                    .map(bookImageService::getBookImage)
                    .collect(Collectors.toList());
                existing.getImages().addAll(newImages);
            }

            // Add new authors (optional: overwrite or add?)
            if (createBookDTO.getAuthorIds() != null && !createBookDTO.getAuthorIds().isEmpty()) {
                Set<Author> newAuthors = createBookDTO.getAuthorIds().stream()
                    .map(authorService::getAuthor)
                    .collect(Collectors.toSet());
                existing.getAuthors().addAll(newAuthors);  // Or use setAuthors() to overwrite
            }

            // Add categories (optional)
            if (createBookDTO.getCategoryIds() != null && !createBookDTO.getCategoryIds().isEmpty()) {
                Set<Category> newCategories = createBookDTO.getCategoryIds().stream()
                    .map(categoryService::getCategory)
                    .collect(Collectors.toSet());
                if (existing.getCategories() == null)
                    existing.setCategories(newCategories);
                else
                    existing.getCategories().addAll(newCategories);
            }

            Book updated = bookRepository.save(existing);
            return toDto(updated);
        }

        
        public void deleteBook(Long id) {
            Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found with ID: " + id));
            bookRepository.delete(book);
    }

}
