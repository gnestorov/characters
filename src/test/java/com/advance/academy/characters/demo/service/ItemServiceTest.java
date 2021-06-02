package com.advance.academy.characters.demo.service;

import com.advance.academy.characters.demo.exception.ItemNotFoundException;
import com.advance.academy.characters.demo.model.Item;
import com.advance.academy.characters.demo.model.Weapon;
import com.advance.academy.characters.demo.model.enumeration.DamageType;
import com.advance.academy.characters.demo.model.enumeration.ItemType;
import com.advance.academy.characters.demo.model.enumeration.WeaponType;
import com.advance.academy.characters.demo.repository.ArmorRepository;
import com.advance.academy.characters.demo.repository.ItemRepository;
import com.advance.academy.characters.demo.repository.ShieldRepository;
import com.advance.academy.characters.demo.repository.WeaponRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

    @Mock
    private WeaponRepository weaponRepository;

    @Mock
    private ArmorRepository armorRepository;

    @Mock
    private ShieldRepository shieldRepository;

    private ItemService itemService;

    @BeforeEach
    public void setUp() {
        itemService = new ItemService(itemRepository, weaponRepository, armorRepository, shieldRepository);
    }

    @Test
    public void verifyFindByIdWorks() {
        Long expectedId = 10L;

        Item staff = Weapon.builder()
                .id(expectedId)
                .itemType(ItemType.WEAPON)
                .weaponType(WeaponType.STAFF)
                .name("Sopa")
                .damage(100)
                .damageType(DamageType.CRUSHING)
                .description("Mnogo boli")
                .build();

        when(itemRepository.findById(expectedId))
                .thenReturn(Optional.of(staff));

        Item actualItem = itemService.findById(expectedId);

        assertEquals(staff.getId(), actualItem.getId());
        verify(itemRepository, times(1))
                .findById(expectedId);
    }

    @Test
    public void verifyFindByIdThrowsItemNotFoundException() {
        Long id = 10L;

        assertThrows(ItemNotFoundException.class, () -> {
            itemService.findById(id);
        });
    }

    @Test
    public void verifyFindByIdThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            itemService.findById(null);
        });
    }

}