package com.advance.academy.characters.demo.repository;

import com.advance.academy.characters.demo.model.Weapon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeaponRepository extends JpaRepository<Weapon, Long> {
}
