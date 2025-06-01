package com.example.UMS.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.UMS.constants.Role;
import com.example.UMS.model.UserProfile;
import com.example.UMS.repository.UserProfileRepository;

@Service
public class UserProfileService {
    @Autowired
    private UserProfileRepository userProfileRepository;

    public List<UserProfile> getAllUsers() {
        return userProfileRepository.findAll();
    }

    public List<UserProfile> getUsersByCustomColumn(String columnName, String value) {
        switch (columnName) {
            case "username":
                return userProfileRepository.findByUsername(value);

            default:
                return List.of();
        }
    }

    public void addUser(UserProfile userProfile) {
        if (userProfileRepository.findByUsername(userProfile.getUsername()) == null) {
            userProfileRepository.save(userProfile);
        } else {
            throw new RuntimeException("Username Already Exists");
        }
    }

    public List<String> addMultipleUsers(List<UserProfile> userProfilesList) {
        List<String> existUsernamesList = new ArrayList<>();
        List<UserProfile> users = new ArrayList<>();
        List<String> dbusernames = userProfileRepository.getListOfUsernames();

        userProfilesList.stream()
            .map(UserProfile -> UserProfile.getUsername())
            .filter(username -> !dbusernames.contains(username))
            .collect(Collectors.toList());

        userProfileRepository.saveAll(users);
        return existUsernamesList;
    }

    public void editUser(UserProfile userProfile) {
        userProfileRepository.save(userProfile);
    }

    public UserProfile getUserById(Long Id) {
        Optional<UserProfile> userprofile = userProfileRepository.findById(Id);

        if (userprofile.isPresent()) {
            return userprofile.get();
        } else {
            return null;
        }
    }

    public List<UserProfile> searchUsers(String subString) {
        return userProfileRepository.findByUsernameContainingOrFullnameContaining(subString, subString);
    }

    public List<UserProfile> getUserByRole(String roleString) {
        Role role = Role.valueOf(roleString);

        return userProfileRepository.findByRole(role);
    }

    // Function Used to change the password of the given user
    public void changePassword(Long userId, String password) {
        UserProfile userProfile = getUserById(userId);

        if (userProfile == null) {
            throw new RuntimeException("Unable to find the user");
        } else {
            userProfile.setPassword(password);
            editUser(userProfile);
        }
    }
}
