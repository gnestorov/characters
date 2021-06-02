package com.advance.academy.characters.demo.repository;

import com.advance.academy.characters.demo.model.DefaultEquipment;
import com.advance.academy.characters.demo.model.enumeration.CharacterClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DefaultEquipmentRepository extends JpaRepository<DefaultEquipment, Long> {

    DefaultEquipment findByCharacterClass(CharacterClass characterClass);

}
