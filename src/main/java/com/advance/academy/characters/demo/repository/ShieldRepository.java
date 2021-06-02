package com.advance.academy.characters.demo.repository;

import com.advance.academy.characters.demo.model.Shield;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShieldRepository extends JpaRepository<Shield, Long> {
}
