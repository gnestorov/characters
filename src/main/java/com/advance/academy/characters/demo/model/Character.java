package com.advance.academy.characters.demo.model;

import com.advance.academy.characters.demo.model.enumeration.CharacterClass;
import com.advance.academy.characters.demo.model.enumeration.Gender;
import com.advance.academy.characters.demo.model.enumeration.Race;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "characters")
public class Character {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z]{3,20}", message = "Name may only contains letters (min - 3, max - 20). Other symbols and empty spaces are forbidden!")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "race", nullable = false)
    private Race race;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "character_class", nullable = false)
    private CharacterClass characterClass;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "characters_items",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items = new HashSet<>();

}
