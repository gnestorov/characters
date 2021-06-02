package com.advance.academy.characters.demo.service;

import com.advance.academy.characters.demo.exception.ItemNotFoundException;
import com.advance.academy.characters.demo.model.Armor;
import com.advance.academy.characters.demo.model.Item;
import com.advance.academy.characters.demo.model.Shield;
import com.advance.academy.characters.demo.model.Weapon;
import com.advance.academy.characters.demo.repository.ArmorRepository;
import com.advance.academy.characters.demo.repository.ItemRepository;
import com.advance.academy.characters.demo.repository.ShieldRepository;
import com.advance.academy.characters.demo.repository.WeaponRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ItemService {

    private final ItemRepository itemRepository;
    private final WeaponRepository weaponRepository;
    private final ArmorRepository armorRepository;
    private final ShieldRepository shieldRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, WeaponRepository weaponRepository, ArmorRepository armorRepository, ShieldRepository shieldRepository) {
        this.itemRepository = itemRepository;
        this.weaponRepository = weaponRepository;
        this.armorRepository = armorRepository;
        this.shieldRepository = shieldRepository;
    }

    public Set<Item> findAllItems() {
        return new HashSet<>(itemRepository.findAll());
    }

    public Set<Weapon> findAllWeapons() {
        return new HashSet<>(weaponRepository.findAll());
    }

    public Set<Armor> findAllArmors() {
        return new HashSet<>(armorRepository.findAll());
    }

    public Set<Shield> findAllShields() {
        return new HashSet<>(shieldRepository.findAll());
    }

    public Item findById(@NonNull Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Could not find item with id '%s'", id)));
    }

    public void createWeapon(@NonNull Weapon weapon) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)
        itemRepository.save(weapon);
    }

    public void createArmor(@NonNull Armor armor) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)
        itemRepository.save(armor);
    }

    public void createShield(@NonNull Shield shield) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)
        itemRepository.save(shield);
    }

    public Weapon updateWeapon(Long id, Weapon weapon) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)
        Weapon updatedWeapon = weaponRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Could not find item with id '%s'", id)));

        updatedWeapon.setItemType(weapon.getItemType());
        updatedWeapon.setWeaponType(weapon.getWeaponType());
        updatedWeapon.setName(weapon.getName());
        updatedWeapon.setDamage(weapon.getDamage());
        updatedWeapon.setDamageType(weapon.getDamageType());
        updatedWeapon.setDescription(weapon.getDescription());

        return itemRepository.save(updatedWeapon);
    }

    public Armor updateArmor(Long id, Armor armor) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)
        Armor updatedArmor = armorRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Could not find item with id '%s'", id)));

        updatedArmor.setItemType(armor.getItemType());
        updatedArmor.setArmorType(armor.getArmorType());
        updatedArmor.setName(armor.getName());
        updatedArmor.setDamageReduction(armor.getDamageReduction());
        updatedArmor.setResistantTo(armor.getResistantTo());
        updatedArmor.setWeakAgainst(armor.getWeakAgainst());
        updatedArmor.setDescription(armor.getDescription());

        return itemRepository.save(updatedArmor);
    }

    public Shield updateShield(Long id, Shield shield) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)
        Shield updatedShield = shieldRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException(String.format("Could not find item with id '%s'", id)));

        updatedShield.setItemType(shield.getItemType());
        updatedShield.setShieldType(shield.getShieldType());
        updatedShield.setDefence(shield.getDefence());
        updatedShield.setDescription(shield.getDescription());

        return itemRepository.save(updatedShield);
    }

    public void deleteById(@NonNull Long id) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)
        itemRepository.deleteById(id);
    }

    public void save(@NonNull Item item) {
        itemRepository.save(item);
    }

}
