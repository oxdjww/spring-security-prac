package com.example.demo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findTokenByIdentifier(String identifier);
}
