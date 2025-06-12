package com.example.UMS.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.UMS.model.Inventory;
import com.example.UMS.service.InventoryService;

@RestController
@RequestMapping("/api/inventory")
@CrossOrigin(origins = "*") // Optional: adjust as needed
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        return ResponseEntity.ok(inventoryService.getAllItemsStockDetails());
    }

    @GetMapping("/{inventoryId}")
    public ResponseEntity<Inventory> getInventoryById(@PathVariable Long inventoryId) {
        Inventory inventory = inventoryService.getStockDetailsWithInventoryId(inventoryId);
        return inventory != null ? ResponseEntity.ok(inventory) : ResponseEntity.notFound().build();
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<Inventory> getInventoryByBookId(@PathVariable Long bookId) {
        Inventory inventory = inventoryService.getStockDetailsWithBookId(bookId);
        return inventory != null ? ResponseEntity.ok(inventory) : ResponseEntity.notFound().build();
    }

    @GetMapping("/stock-equals/{quantity}")
    public ResponseEntity<List<Inventory>> getStocksWithExactQuantity(@PathVariable Integer quantity) {
        return ResponseEntity.ok(inventoryService.getStocksWithNQunatity(quantity));
    }

    @GetMapping("/stock-less-than/{quantity}")
    public ResponseEntity<List<Inventory>> getStocksWithLessThanQuantity(@PathVariable Integer quantity) {
        return ResponseEntity.ok(inventoryService.getStocksUnderNQunatity(quantity));
    }

    @PostMapping
    public ResponseEntity<String> addNewInventory(@RequestBody Inventory inventory) {
        inventoryService.addStock(inventory);
        return ResponseEntity.ok("Inventory added successfully.");
    }

    @PutMapping("/update-by-id/{inventoryId}")
    public ResponseEntity<String> updateStockByInventoryId(@PathVariable Long inventoryId, 
                                                           @RequestParam Integer quantity) {
        try {
            inventoryService.updateStockByInventoryId(inventoryId, quantity);
            return ResponseEntity.ok("Stock updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/update-by-book/{bookId}")
    public ResponseEntity<String> updateStockByBookId(@PathVariable Long bookId, 
                                                      @RequestParam Integer quantity) {
        try {
            inventoryService.updateStockByBookId(bookId, quantity);
            return ResponseEntity.ok("Stock updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
