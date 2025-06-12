package com.example.UMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UMS.model.BookImage;

public interface BookImageRepository extends JpaRepository<BookImage, Long> {
    public List<BookImage> findByBookBookIdAndIsCoverImage(Long id, Boolean coverPage);
}
