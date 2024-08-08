package com.example.LibraryManagementSystem.repository;

import com.example.LibraryManagementSystem.entity.Role;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
