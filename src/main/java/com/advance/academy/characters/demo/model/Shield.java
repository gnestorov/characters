package com.advance.academy.characters.demo.model;

import com.advance.academy.characters.demo.model.enumeration.ItemType;
import com.advance.academy.characters.demo.model.enumeration.ShieldType;
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
@Table(name = "shields")
public class Shield extends Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type", nullable = false)
    private ItemType itemType;

    @Enumerated(EnumType.STRING)
    @Column(name = "shield_type", nullable = false)
    private ShieldType shieldType;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "defence", nullable = false)
    private double defence;

    @Column(name = "description", nullable = false)
    private String description;
}
