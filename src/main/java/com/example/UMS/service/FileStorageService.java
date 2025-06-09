package com.example.UMS.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {
    @Value("${upload.dir}")
    private String UPLOAD_DIR;
    
    public String storeImage(MultipartFile file) {
        validateFile(file);
        try {
            String filename = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path targePath = Paths.get(UPLOAD_DIR).resolve(filename);
            Files.createDirectories(targePath.getParent());
            Files.copy(file.getInputStream(), targePath, StandardCopyOption.REPLACE_EXISTING);
            return targePath.toString();
        } catch (IOException e) {
            throw new RuntimeException("File storage error" + e.getMessage());  
        }
    }

    public void validateFile(MultipartFile file) {
        String contentType = file.getContentType();

        if (file.isEmpty()) {
            throw new RuntimeException("File is Empty!");
        }

        if(contentType == null || !contentType.startsWith("image/")) {
            throw new RuntimeException("file is not an image");
        }
        
        if (file.getSize() > (5 * 1024 * 1024)) {
            throw new RuntimeException("file Size exceeds 5 MB");
        }
    }
}
