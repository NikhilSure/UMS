package com.example.UMS.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.UMS.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Category name methods
    List<Category> findByName(String name);
    List<Category> findByNameContainingIgnoreCase(String subString);
    List<Category> findByNameStartingWith(String subString);
    List<Category> findByNameNot(String name);
    List<Category> findByNameIn(List<String> names);
    List<Category> findByNameNotIn(List<String> names);
    @Query("SELECT p.name FROM Category p")
    List<String> getListOfNames();
}
