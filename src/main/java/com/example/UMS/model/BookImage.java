package com.example.UMS.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "book_image")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    private String imageUrl;

    private Boolean isCoverImage;
}

