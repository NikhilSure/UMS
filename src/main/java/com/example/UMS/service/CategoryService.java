package com.example.UMS.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UMS.dto.CategoryDTO;
import com.example.UMS.model.Category;
import com.example.UMS.repository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public CategoryDTO toDto(Category category) {
        return category == null ?
         null : CategoryDTO
                    .builder()
                    .id(category.getCategoryId())
                    .name(category.getName())
                    .build();
    }

    public List<Category> getAllCategorys() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);    
    }

    public void deleteCategory(Long id) {
        Category category = getCategory(id);

        if (category == null) {
            throw new RuntimeException("No category with thw given Id");
        }
        
        categoryRepository.deleteById(id);
    }

    public Category getCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) return category.get(); else return null;
    }

    public Category updateCategoryName(Long categoryId, String name) {
        Category category = getCategory(categoryId);
        
        if (category == null) {
            throw new RuntimeException("Unable to find category with the given Id");
        } else {
            category.setName(name);
        }

        return categoryRepository.save(category) ;
    }
}
