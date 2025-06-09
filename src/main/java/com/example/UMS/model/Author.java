package com.example.UMS.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "author")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long authorId;

    @Column(nullable = false, length = 255)
    private String fullname;

    @Lob
    private String bio;

    private String profilePicUrl;
}
