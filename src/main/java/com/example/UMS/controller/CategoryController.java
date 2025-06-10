package com.example.UMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.UMS.model.Category;
import com.example.UMS.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @GetMapping
    public List<Category> getCategories() {
        return categoryService.getAllCategorys();
    }

    @PostMapping
    public String addCategory(@RequestParam String name) {
        Category category = Category.builder().name(name).build();
        categoryService.addCategory(category);
        return "Added Successfully!";
    }

    @PutMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id, @RequestParam String name) {
        if (id == null || name == null || name.isEmpty()) {
            throw new RuntimeException("Invalid payload, need categoryId and name!");
        }

        categoryService.updateCategoryName(id, name);
        return "updated Successfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deletecategory(@PathVariable Long id) {
        if(id == null) {
            throw new RuntimeException("Invalid category id");
        }

        categoryService.deleteCategory(id);
        return "deleted!";
    }
}
