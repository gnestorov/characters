package com.advance.academy.characters.demo.model;

import com.advance.academy.characters.demo.model.enumeration.CharacterClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "default_equipments")
public class DefaultEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "character_class", unique = true, nullable = false)
    private CharacterClass characterClass;

    @ManyToMany()
    @JoinTable(
            name = "default_equipments_items",
            joinColumns = @JoinColumn(name = "character_class_id"),
            inverseJoinColumns = @JoinColumn(name = "item_id")
    )
    private Set<Item> items = new HashSet<>();


}
