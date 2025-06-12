package com.example.UMS.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookImageDTO {
    private Long id;
    private String imageUrl;
    private boolean isCoverImage;
}
