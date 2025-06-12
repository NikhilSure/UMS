package com.example.UMS.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UMS.dto.BookImageDTO;
import com.example.UMS.model.BookImage;
import com.example.UMS.repository.BookImageRepository;

@Service
public class BookImageService {
    @Autowired
    BookImageRepository bookImageRepository;

    public BookImageDTO toDto(BookImage image) {
    return BookImageDTO.builder()
        .id(image.getImageId())
        .imageUrl(image.getImageUrl())
        .isCoverImage(image.getIsCoverImage())
        .build();
    }

    public List<BookImage> getAllBookImages() {
        return bookImageRepository.findAll();
    }

    public void addBookImage(BookImage bookImage) {
        bookImageRepository.save(bookImage  );    
    }

    public BookImage getBookImage(Long bookImageId) {
        Optional<BookImage> bookImage = bookImageRepository.findById(bookImageId);
        if (bookImage.isPresent()) return bookImage.get(); else return null;
    }

    public List<BookImage> getAllCoverImagesForBook(Long id) {
        List<BookImage> bookImages = bookImageRepository.findByBookBookIdAndIsCoverImage(id, true);
        return bookImages;
    } 

     public BookImage updateBookImage(Long imageId, BookImage updatedImage) {
        Optional<BookImage> existingImageOpt = bookImageRepository.findById(imageId);
        if (existingImageOpt.isPresent()) {
            BookImage existingImage = existingImageOpt.get();
            existingImage.setImageUrl(updatedImage.getImageUrl());
            existingImage.setIsCoverImage(updatedImage.getIsCoverImage());
            if (updatedImage.getBook() != null) {
                existingImage.setBook(updatedImage.getBook());
            }
            return bookImageRepository.save(existingImage);
        }
        return null;
    }
}
