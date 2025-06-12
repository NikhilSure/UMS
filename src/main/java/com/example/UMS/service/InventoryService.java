package com.example.UMS.service;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UMS.dto.InventoryDTO;
import com.example.UMS.model.Inventory;
import com.example.UMS.repository.InventoryRepository;

@Service
public class InventoryService {
    @Autowired
    InventoryRepository inventoryRepository;
    
    public InventoryDTO toDTO(Inventory inventory) {
        if (inventory == null) return null;
        return InventoryDTO.builder()
            .inventoryId(inventory.getInventoryId())
            .stockQuantity(inventory.getStockQuantity())
            .build();
    }

    public List<Inventory> getAllItemsStockDetails() {
        return inventoryRepository.findAll();
    }

    public void addStock(Inventory inventory) {
        inventoryRepository.save(inventory);    
    }

    public Inventory getStockDetailsWithInventoryId(Long inventoryId) {
        Optional<Inventory> inventory = inventoryRepository.findById(inventoryId);
        if (inventory.isPresent()) return inventory.get(); else return null;
    }

    public Inventory getStockDetailsWithBookId(Long id) {
        List<Inventory> inventory = inventoryRepository.findByBookBookId(id);
        if (inventory.isEmpty()) {
            return null;
        }

        return inventory.get(0);
    }

    public List<Inventory> getStocksWithNQunatity(Integer n) {
        return inventoryRepository.findByStockQuantity(n);
    }

    public List<Inventory> getStocksUnderNQunatity(Integer n) {
        return inventoryRepository.findByStockQuantityLessThanOrderByStockQuantityAsc(n);
    }

   public void updateStockByInventoryId(Long Id, Integer stockQuantity) {
        Inventory inventory = getStockDetailsWithInventoryId(Id);

        if(inventory == null) {
            throw new RuntimeException("No Inventory is mapped to the id");
        }

        inventory.setStockQuantity(stockQuantity);
        inventoryRepository.save(inventory);    
   }

   public void updateStockByBookId(Long Id, Integer stockQuantity) {
        Inventory inventory = getStockDetailsWithBookId(Id);

        if(inventory == null) {
            throw new RuntimeException("No Inventory is mapped to the id");
        }

        inventory.setStockQuantity(stockQuantity);
        inventoryRepository.save(inventory);    
   }

}
