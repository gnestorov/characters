package com.advance.academy.characters.demo.service;

import com.advance.academy.characters.demo.dto.CharacterDto;
import com.advance.academy.characters.demo.exception.CharacterNotFoundException;
import com.advance.academy.characters.demo.exception.ItemNotFoundException;
import com.advance.academy.characters.demo.model.Character;
import com.advance.academy.characters.demo.model.Item;
import com.advance.academy.characters.demo.model.Weapon;
import com.advance.academy.characters.demo.model.enumeration.*;
import com.advance.academy.characters.demo.repository.CharacterRepository;
import com.advance.academy.characters.demo.repository.DefaultEquipmentRepository;
import com.advance.academy.characters.demo.repository.ItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class CharacterServiceTest {

    @Mock
    private CharacterRepository characterRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private DefaultEquipmentRepository defaultEquipmentRepository;

    @Mock
    private ItemRepository itemRepository;

    private CharacterService characterService;

    @BeforeEach
    public void setUp() {
        characterService = new CharacterService(characterRepository, modelMapper, defaultEquipmentRepository, itemRepository);
    }

    @Test
    public void verifyFindByNameWorks() {
        String expectedName = "Thrall";

        Character character = Character.builder()
                .id(10L)
                .name(expectedName)
                .race(Race.ORC)
                .gender(Gender.MALE)
                .characterClass(CharacterClass.WARRIOR)
                .createdAt(LocalDate.now())
                .build();

        when(characterRepository.findByName(expectedName))
                .thenReturn(Optional.of(character));

        CharacterDto characterDto = characterService.findByName(expectedName);

        assertEquals(character.getName(), characterDto.getName());
        verify(characterRepository, times(1))
                .findByName(expectedName);
    }

    @Test
    public void verifyFindByIdWorks() {
        Long expectedId = 10L;

        Character character = Character.builder()
                .id(expectedId)
                .name("Thrall")
                .race(Race.ORC)
                .gender(Gender.MALE)
                .characterClass(CharacterClass.WARRIOR)
                .createdAt(LocalDate.now())
                .build();

        when(characterRepository.findById(expectedId))
                .thenReturn(Optional.of(character));

        CharacterDto characterDto = characterService.findById(expectedId);

        assertEquals(character.getId(), characterDto.getId());
        verify(characterRepository, times(1))
                .findById(expectedId);
    }

    @Test
    public void verifyFindByNameThrowsCharacterNotFoundException() {
        String expectedName = "Thrall";

        assertThrows(CharacterNotFoundException.class, () -> {
            characterService.findByName(expectedName);
        });
    }

    @Test
    public void verifyFindByIdThrowsCharacterNotFoundException() {
        Long id = 10L;

        assertThrows(CharacterNotFoundException.class, () -> {
            characterService.findById(id);
        });
    }

    @Test
    public void verifyFindByNameThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            characterService.findByName(null);
        });
    }

    @Test
    public void verifyFindByIdThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> {
            characterService.findById(null);
        });
    }
}