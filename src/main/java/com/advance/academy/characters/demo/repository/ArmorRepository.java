package com.advance.academy.characters.demo.repository;

import com.advance.academy.characters.demo.model.Armor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArmorRepository extends JpaRepository<Armor, Long> {
}
