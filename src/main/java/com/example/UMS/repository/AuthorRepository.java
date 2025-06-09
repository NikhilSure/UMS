package com.example.UMS.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.UMS.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    // username methods
    List<Author> findByFullname(String fullname);
    List<Author> findByFullnameContainingIgnoreCase(String subString);
    List<Author> findByFullnameStartingWith(String subString);
    List<Author> findByFullnameNot(String fullname);
    List<Author> findByFullnameIn(List<String> fullnames);
    List<Author> findByFullnameNotIn(List<String> fullnames);
    @Query("SELECT a.fullname FROM Author a")
    List<String> getListOfUsernames();
}
