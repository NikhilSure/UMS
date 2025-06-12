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
public class CreateBookDTO {
    private Long id;
    private String title;
    private String isbn;
    private Double price;
    private String description;
    private LocalDateTime publishedDate;
    private Long publisherId;
    private List<Long> authorIds;
    private List<Long> categoryIds;
    private InventoryDTO inventoryId;
    private List<Long> imagesIds;
}
