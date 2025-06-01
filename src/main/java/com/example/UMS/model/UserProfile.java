package com.example.UMS.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

import com.example.UMS.constants.Role;

@Entity
@Table(name = "user_profile")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(unique = true, nullable = false, length = 50)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(length = 100)
    private String fullname;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private String profilePicUrl;
    
    @Lob
    private String address;
    
    
    @PrePersist
    private void onCreate(){
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now(); 
        }
    }
    
    @PreUpdate
    private void onUpdate() {
        if (updatedAt == null) {
            updatedAt = LocalDateTime.now();
        }
    }
}
