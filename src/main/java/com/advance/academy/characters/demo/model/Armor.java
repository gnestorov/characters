package com.advance.academy.characters.demo.model;

import com.advance.academy.characters.demo.model.enumeration.ArmorType;
import com.advance.academy.characters.demo.model.enumeration.ItemType;
import com.advance.academy.characters.demo.model.enumeration.Resistance;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "armors")
public class Armor extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable = false)
    private ItemType itemType;

    @Enumerated(EnumType.STRING)
    @Column(name = "armor_type", nullable = false)
    private ArmorType armorType;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "damage_reduction", nullable = false)
    private double damageReduction;

    @Enumerated(EnumType.STRING)
    @Column(name = "resistant_to", nullable = false)
    private Resistance resistantTo;

    @Enumerated(EnumType.STRING)
    @Column(name = "weak_against")
    private Resistance weakAgainst;

    @Column(name = "description", nullable = false)
    private String description;

}
