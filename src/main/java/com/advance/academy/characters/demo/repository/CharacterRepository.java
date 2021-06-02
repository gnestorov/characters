package com.advance.academy.characters.demo.repository;

import com.advance.academy.characters.demo.model.Character;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<Character, Long> {

    Optional<Character> findByName(String name);

    void deleteByName(String name);
}
