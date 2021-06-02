package com.advance.academy.characters.demo.controller;

import com.advance.academy.characters.demo.model.Armor;
import com.advance.academy.characters.demo.model.Item;
import com.advance.academy.characters.demo.model.Shield;
import com.advance.academy.characters.demo.model.Weapon;
import com.advance.academy.characters.demo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/items")
public class ItemController {

    private final ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<Set<Item>> findAllItems() {
        return ResponseEntity.ok(itemService.findAllItems());
    }

    @GetMapping(value = "/weapons")
    public ResponseEntity<Set<Weapon>> findAllWeapons() {
        return ResponseEntity.ok(itemService.findAllWeapons());
    }

    @GetMapping(value = "/armors")
    public ResponseEntity<Set<Armor>> findAllArmors() {
        return ResponseEntity.ok(itemService.findAllArmors());
    }

    @GetMapping(value = "/shields")
    public ResponseEntity<Set<Shield>> findAllShields() {
        return ResponseEntity.ok(itemService.findAllShields());
    }

    @GetMapping(value = "/{id}")
    public Item findById(@PathVariable(value = "id") Long id) {
        return itemService.findById(id);
    }

    @PostMapping(value = "/weapons")
    public void createWeapon(@RequestBody Weapon weapon) {
        itemService.createWeapon(weapon);
    }

    @PostMapping(value = "/armors")
    public void createArmor(@RequestBody Armor armor) {
        itemService.createArmor(armor);
    }

    @PostMapping(value = "/shields")
    public void createShield(@RequestBody Shield shield) {
        itemService.createShield(shield);
    }

    @PutMapping(value = "/weapons/{id}")
    public ResponseEntity<Weapon> updateWeapon(@PathVariable(value = "id") Long id, @RequestBody Weapon weapon) {
        Weapon updatedWeapon = itemService.updateWeapon(id, weapon);

        return ResponseEntity.ok(updatedWeapon);
    }

    @PutMapping(value = "/armors/{id}")
    public ResponseEntity<Armor> updateWeapon(@PathVariable(value = "id") Long id, @RequestBody Armor armor) {
        Armor updatedArmor = itemService.updateArmor(id, armor);

        return ResponseEntity.ok(updatedArmor);
    }

    @PutMapping(value = "/shields/{id}")
    public ResponseEntity<Shield> updateShield(@PathVariable(value = "id") Long id, @RequestBody Shield shield) {
        Shield updatedShield = itemService.updateShield(id, shield);

        return ResponseEntity.ok(updatedShield);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id) {
        itemService.deleteById(id);

        return ResponseEntity.ok().build();
    }


}
