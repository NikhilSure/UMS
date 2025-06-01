package com.example.UMS.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventory")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inventoryId;

    @OneToOne
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    private Integer stockQuantity;

    private java.time.LocalDateTime lastRestocked;
}

