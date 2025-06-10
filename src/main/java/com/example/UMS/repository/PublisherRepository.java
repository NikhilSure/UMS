package com.example.UMS.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.UMS.model.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    // userName methods
    List<Publisher> findByName(String name);
    List<Publisher> findByNameContainingIgnoreCase(String subString);
    List<Publisher> findByNameStartingWith(String subString);
    List<Publisher> findByNameNot(String name);
    List<Publisher> findByNameIn(List<String> names);
    List<Publisher> findByNameNotIn(List<String> names);
    @Query("SELECT p.name FROM Publisher p")
    List<String> getListOfNames();
}
