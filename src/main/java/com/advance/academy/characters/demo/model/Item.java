package com.advance.academy.characters.demo.model;

import com.advance.academy.characters.demo.model.enumeration.ItemType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "items")
@Inheritance(strategy = InheritanceType.JOINED)
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "item_type")
    private ItemType itemType;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @JsonBackReference
    @ManyToMany(mappedBy = "items")
    private Set<Character> characters = new HashSet<>();

}
