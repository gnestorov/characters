package com.advance.academy.characters.demo;

import com.advance.academy.characters.demo.model.*;
import com.advance.academy.characters.demo.model.enumeration.*;
import com.advance.academy.characters.demo.repository.DefaultEquipmentRepository;
import com.advance.academy.characters.demo.service.ItemService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class CharactersDemoApplication {

    private final ItemService itemService;
    private final DefaultEquipmentRepository defaultEquipmentRepository;

    public CharactersDemoApplication(ItemService itemService, DefaultEquipmentRepository defaultEquipmentRepository) {
        this.itemService = itemService;
        this.defaultEquipmentRepository = defaultEquipmentRepository;
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {
        SpringApplication.run(CharactersDemoApplication.class, args);
    }

    @PostConstruct
    public void createDefaultEquipment() {

        Item dagger = Weapon.builder()
                .itemType(ItemType.WEAPON)
                .weaponType(WeaponType.DAGGER)
                .name("Assassin's dagger")
                .damage(10)
                .damageType(DamageType.PIERCING)
                .description("Low damage but very fast")
                .build();
        itemService.save(dagger);

        Item longsword = Weapon.builder()
                .itemType(ItemType.WEAPON)
                .weaponType(WeaponType.LONGSWORD)
                .name("Steel longsword")
                .damage(25)
                .damageType(DamageType.SLASHING)
                .description("Good mixture of attack speed and damage")
                .build();
        itemService.save(longsword);

        Item mace = Weapon.builder()
                .itemType(ItemType.WEAPON)
                .weaponType(WeaponType.MACE)
                .name("Blessed mace")
                .damage(20)
                .damageType(DamageType.CRUSHING)
                .description("Deals bonus damage to undead")
                .build();
        itemService.save(mace);

        Item staff = Weapon.builder()
                .itemType(ItemType.WEAPON)
                .weaponType(WeaponType.STAFF)
                .name("Sopa")
                .damage(100)
                .damageType(DamageType.CRUSHING)
                .description("Mnogo boli")
                .build();
        itemService.save(staff);

        Item greatSword = Weapon.builder()
                .itemType(ItemType.WEAPON)
                .weaponType(WeaponType.GREATSWORD)
                .name("Gladiator's Blade")
                .damage(50)
                .damageType(DamageType.SLASHING)
                .description("Huge, heavy and very damaging")
                .build();
        itemService.save(greatSword);

        Item shortbow = Weapon.builder()
                .itemType(ItemType.WEAPON)
                .weaponType(WeaponType.SHORTBOW)
                .name("Assassin's bow")
                .damage(15)
                .damageType(DamageType.PIERCING)
                .description("Shoots poison arrows")
                .build();
        itemService.save(shortbow);

        Item longbow = Weapon.builder()
                .itemType(ItemType.WEAPON)
                .weaponType(WeaponType.LONGBOW)
                .name("Composite bow")
                .damage(30)
                .damageType(DamageType.PIERCING)
                .description("Large and heavy, but good range and damage")
                .build();
        itemService.save(longbow);

        Item cloathing = Armor.builder()
                .itemType(ItemType.ARMOR)
                .armorType(ArmorType.CLOATHING)
                .name("Mage's robe")
                .damageReduction(5)
                .resistantTo(Resistance.NONE)
                .weakAgainst(Resistance.ALL)
                .description("Very light, but offers almost no protection")
                .build();
        itemService.save(cloathing);

        Item lightArmor = Armor.builder()
                .itemType(ItemType.ARMOR)
                .armorType(ArmorType.LIGHT)
                .name("Leather armor")
                .damageReduction(20)
                .resistantTo(Resistance.NONE)
                .weakAgainst(Resistance.PIERCING)
                .description("Light and comfortable")
                .build();
        itemService.save(lightArmor);

        Item mediumArmor = Armor.builder()
                .itemType(ItemType.ARMOR)
                .armorType(ArmorType.MEDIUM)
                .name("Chainmail armor")
                .damageReduction(45)
                .resistantTo(Resistance.SLASHING)
                .weakAgainst(Resistance.CRUSHING)
                .description("Offers good mobility and reasonable protection")
                .build();
        itemService.save(mediumArmor);

        Item heavyArmor = Armor.builder()
                .itemType(ItemType.ARMOR)
                .armorType(ArmorType.HEAVY)
                .name("Full plate armor")
                .damageReduction(75)
                .resistantTo(Resistance.ALL)
                .weakAgainst(Resistance.NONE)
                .description("Very heavy, but excellent protection")
                .build();
        itemService.save(heavyArmor);

        Item mediumShield = Shield.builder()
                .itemType(ItemType.SHIELD)
                .shieldType(ShieldType.MEDIUM)
                .name("Iron round shield")
                .defence(45)
                .description("Very sturdy for it's weight")
                .build();
        itemService.save(mediumShield);

        Item heavyShield = Shield.builder()
                .itemType(ItemType.SHIELD)
                .shieldType(ShieldType.HEAVY)
                .name("Iron tower shield")
                .defence(75)
                .description("Very heavy, but almost indestructible")
                .build();
        itemService.save(heavyShield);

        Set<Item> warriorItems = new HashSet<>();
        warriorItems.add(greatSword);
        warriorItems.add(mediumArmor);

        Set<Item> rogueItems = new HashSet<>();
        rogueItems.add(dagger);
        rogueItems.add(shortbow);
        rogueItems.add(lightArmor);

        Set<Item> mageItems = new HashSet<>();
        mageItems.add(staff);
        mageItems.add(cloathing);

        Set<Item> clericItems = new HashSet<>();
        clericItems.add(mace);
        clericItems.add(mediumShield);
        clericItems.add(heavyArmor);

        Set<Item> rangerItems = new HashSet<>();
        rangerItems.add(longsword);
        rangerItems.add(longbow);
        rangerItems.add(lightArmor);

        Set<Item> paladinItems = new HashSet<>();
        paladinItems.add(longsword);
        paladinItems.add(heavyShield);
        paladinItems.add(heavyArmor);

        DefaultEquipment warriorEquipment = DefaultEquipment.builder()
                .characterClass(CharacterClass.WARRIOR)
                .items(warriorItems)
                .build();
        defaultEquipmentRepository.save(warriorEquipment);

        DefaultEquipment rogueEquipment = DefaultEquipment.builder()
                .characterClass(CharacterClass.ROGUE)
                .items(rogueItems)
                .build();
        defaultEquipmentRepository.save(rogueEquipment);

        DefaultEquipment mageEquipment = DefaultEquipment.builder()
                .characterClass(CharacterClass.MAGE)
                .items(mageItems)
                .build();
        defaultEquipmentRepository.save(mageEquipment);

        DefaultEquipment clericEquipment = DefaultEquipment.builder()
                .characterClass(CharacterClass.CLERIC)
                .items(clericItems)
                .build();
        defaultEquipmentRepository.save(clericEquipment);

        DefaultEquipment rangerEquipment = DefaultEquipment.builder()
                .characterClass(CharacterClass.RANGER)
                .items(rangerItems)
                .build();
        defaultEquipmentRepository.save(rangerEquipment);

        DefaultEquipment paladinEquipment = DefaultEquipment.builder()
                .characterClass(CharacterClass.PALADIN)
                .items(paladinItems)
                .build();
        defaultEquipmentRepository.save(paladinEquipment);

    }


}
