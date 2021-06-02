package com.advance.academy.characters.demo.model;

import com.advance.academy.characters.demo.model.enumeration.DamageType;
import com.advance.academy.characters.demo.model.enumeration.ItemType;
import com.advance.academy.characters.demo.model.enumeration.WeaponType;
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
@Table(name = "weapons")
public class Weapon extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable = false)
    private ItemType itemType;

    @Enumerated(EnumType.STRING)
    @Column(name = "weapon_type", nullable = false)
    private WeaponType weaponType;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "damage", nullable = false)
    private double damage;

    @Enumerated(EnumType.STRING)
    @Column(name = "damage_type", nullable = false)
    private DamageType damageType;

    @Column(name = "description", nullable = false)
    private String description;


}
