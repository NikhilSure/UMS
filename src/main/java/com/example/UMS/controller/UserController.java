package com.example.UMS.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.UMS.constants.Role;
import com.example.UMS.model.UserProfile;
import com.example.UMS.service.UserProfileService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserProfileService userProfileService;

    @GetMapping
    public List<UserProfile> getUsersList() {
        return userProfileService.getAllUsers();
    }

    @PostMapping
    public String insertData(@RequestBody UserProfile user) {
        userProfileService.addUser(user);
        return "Added user Successfully";
    }

    @PostMapping("/sampleData")
    public String insertSampleData() {
        UserProfile user1 = UserProfile.builder()
                .username("john_doe")
                .password("password123")
                .email("john@example.com")
                .fullname("John Doe")
                .role(Role.CUSTOMER)
                .profilePicUrl("http://example.com/images/john.jpg")
                .address("123 Main Street, New York")
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        UserProfile admin = UserProfile.builder()
                .username("admin")
                .password("adminpass")
                .email("admin@example.com")
                .fullname("Admin User")
                .role(Role.ADMIN)
                .profilePicUrl("")
                .address("456 Admin Road, Washington")
                .build();

        List<String> existedUsernames = userProfileService.addMultipleUsers(List.of(user1, admin));

        if (existedUsernames.size() > 0) {
            return "existing users list" + existedUsernames;
        } else {
            return "Data inserted SuccessFully";
        }
    }

    @PostMapping("/changepassword")
    public String changePassword(@RequestBody Map<String, Object> body) {
        if (body.containsKey("userId") && body.containsKey("password") ) {
            userProfileService.changePassword((Long)body.get("userId"), (String)body.get("password"));
            return "Password SuccessFully changed"; 
        } else {
            throw new RuntimeException("unable to process the payload");
        }
    }
}
