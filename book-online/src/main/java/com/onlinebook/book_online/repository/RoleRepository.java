package com.onlinebook.book_online.repository;

import com.onlinebook.book_online.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository{// extends JpaRepository<Role, Integer> {

    Optional<Role> findByName(String name);
}
