package com.example.UMS.repository;

import com.example.UMS.constants.Role;
import com.example.UMS.model.UserProfile;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
    // username methods
    List<UserProfile> findByUsername(String username);
    List<UserProfile> findByUsernameContainingIgnoreCase(String subString);
    List<UserProfile> findByUsernameStartingWith(String subString);
    List<UserProfile> findByUsernameNot(String username);
    List<UserProfile> findByUsernameIn(List<String> usernames);
    List<UserProfile> findByUsernameNotIn(List<String> usernames);
    @Query("SELECT u.username FROM user_profile u")
    List<String> getListOfUsernames();

    //address
    // List<UserProfile> findByAddressContainingIgnoreCase(String subString);
    List<UserProfile> findByAddressLike(String pattern);

    // role
    List<UserProfile> findByRole(Role role);
    List<UserProfile> findByUsernameContainingOrFullnameContaining(String usernameSubString,String fullnameSubString);
}
