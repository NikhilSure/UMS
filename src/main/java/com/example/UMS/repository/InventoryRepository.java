package com.example.UMS.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.UMS.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    public List<Inventory> findByBookBookId(Long id);
    public List<Inventory> findByStockQuantity(Integer stockQuantity);
    public List<Inventory> findByStockQuantityLessThanOrderByStockQuantityAsc(Integer stockQuantity);
}
