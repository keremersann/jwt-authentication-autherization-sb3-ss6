package com.kerem.jwtauthenticationautherizationsb3ss6.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    // Since emails are unique, we are going to find users by email
    Optional<User> findByEmail(String email);
}
