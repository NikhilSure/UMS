package com.example.UMS.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "publisher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long publisherId;

    @Column(nullable = false, length = 255)
    private String name;

    @Lob
    private String contactInfo;
}
