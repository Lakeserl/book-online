package com.onlinebook.book_online.repository;

import com.onlinebook.book_online.models.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {


    Optional<Token> findByToken(String token);
}
