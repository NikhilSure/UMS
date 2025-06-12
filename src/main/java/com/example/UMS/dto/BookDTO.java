package com.example.UMS.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {
    private Long id;
    private String title;
    private String isbn;
    private Double price;
    private String description;
    private LocalDateTime publishedDate;
    private String publisherName;
    private List<String> authorNames;
    private List<CategoryDTO> categories;
    private InventoryDTO inventory;
    private List<BookImageDTO> images;
}
