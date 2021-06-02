package com.advance.academy.characters.demo.dto;

import com.advance.academy.characters.demo.model.Item;
import com.advance.academy.characters.demo.model.enumeration.CharacterClass;
import com.advance.academy.characters.demo.model.enumeration.Gender;
import com.advance.academy.characters.demo.model.enumeration.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CharacterDto {

    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Race race;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private CharacterClass characterClass;

    private Set<Item> items;
}
