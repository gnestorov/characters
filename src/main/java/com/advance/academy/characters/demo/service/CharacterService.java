package com.advance.academy.characters.demo.service;

import com.advance.academy.characters.demo.dto.CharacterDto;
import com.advance.academy.characters.demo.exception.CharacterNotFoundException;
import com.advance.academy.characters.demo.model.Character;
import com.advance.academy.characters.demo.model.Item;
import com.advance.academy.characters.demo.repository.CharacterRepository;
import com.advance.academy.characters.demo.repository.DefaultEquipmentRepository;
import com.advance.academy.characters.demo.repository.ItemRepository;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class CharacterService {

    private final CharacterRepository characterRepository;
    private final ModelMapper modelMapper;
    private final DefaultEquipmentRepository defaultEquipmentRepository;
    private final ItemRepository itemRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository, ModelMapper modelMapper, DefaultEquipmentRepository defaultEquipmentRepository, ItemRepository itemRepository) {
        this.characterRepository = characterRepository;
        this.modelMapper = modelMapper;
        this.defaultEquipmentRepository = defaultEquipmentRepository;
        this.itemRepository = itemRepository;
    }

    public CharacterDto getTemplate() {
        return new CharacterDto();
    }

    public Set<CharacterDto> findAll() {

        List<Character> characters = characterRepository.findAll();
        Set<CharacterDto> collection = new HashSet<>();

        for (Character character : characters) {

            CharacterDto characterDto = modelMapper.map(character, CharacterDto.class);
            collection.add(characterDto);
        }
        return collection;
    }

    public CharacterDto findByName(@NonNull String name) {

        Character character = characterRepository.findByName(name)
                .orElseThrow(() -> new CharacterNotFoundException(String.format("Could not find character with name '%s'", name)));

        CharacterDto characterDto = new CharacterDto();
        characterDto.setId(character.getId());
        characterDto.setName(character.getName());
        characterDto.setRace(character.getRace());
        characterDto.setGender(character.getGender());
        characterDto.setCharacterClass(character.getCharacterClass());
        characterDto.setItems(character.getItems());

        return characterDto;
    }

    public CharacterDto findById(@NonNull Long id) {

        Character character = characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(String.format("Could not find character with id '%s'", id)));

        CharacterDto characterDto = new CharacterDto();
        characterDto.setId(character.getId());
        characterDto.setName(character.getName());
        characterDto.setRace(character.getRace());
        characterDto.setGender(character.getGender());
        characterDto.setCharacterClass(character.getCharacterClass());
        characterDto.setItems(character.getItems());

        return characterDto;
    }

    public void create(@NonNull CharacterDto characterDto) {

        Set<Item> copy = new HashSet<>(defaultEquipmentRepository.findByCharacterClass(characterDto.getCharacterClass()).getItems());

        Character character = new Character();
        character.setName(characterDto.getName());
        character.setRace(characterDto.getRace());
        character.setGender(characterDto.getGender());
        character.setCharacterClass(characterDto.getCharacterClass());
        character.setCreatedAt(LocalDate.now());
        character.setItems(copy);

        characterRepository.save(character);
    }

    public Character updateByName(String name, CharacterDto characterDto) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)

        Character updatedCharacter = characterRepository.findByName(name)
                .orElseThrow(() -> new CharacterNotFoundException(String.format("Could not find character with name '%s'", name)));

        List<Long> itemIds = characterDto.getItems().stream().map(Item::getId).collect(Collectors.toList());

        updatedCharacter.setName(characterDto.getName());
        updatedCharacter.setRace(characterDto.getRace());
        updatedCharacter.setGender(characterDto.getGender());
        updatedCharacter.setCharacterClass(characterDto.getCharacterClass());
        updatedCharacter.setItems(new HashSet<>(itemRepository.findAllById(itemIds)));

        return characterRepository.save(updatedCharacter);
    }

    public Character updateById(Long id, CharacterDto characterDto) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)

        Character updatedCharacter = characterRepository.findById(id)
                .orElseThrow(() -> new CharacterNotFoundException(String.format("Could not find character with id '%s'", id)));

        List<Long> itemIds = characterDto.getItems().stream().map(Item::getId).collect(Collectors.toList());

        updatedCharacter.setName(characterDto.getName());
        updatedCharacter.setRace(characterDto.getRace());
        updatedCharacter.setGender(characterDto.getGender());
        updatedCharacter.setCharacterClass(characterDto.getCharacterClass());
        updatedCharacter.setItems(new HashSet<>(itemRepository.findAllById(itemIds)));

        return characterRepository.save(updatedCharacter);
    }

    public void deleteByName(@NonNull String name) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)
        characterRepository.deleteByName(name);
    }

    public void deleteById(@NonNull Long id) {
        // TODO: check if user is admin else throw HttpStatusCodeException(HttpStatus.UNAUTHORIZED)
        characterRepository.deleteById(id);
    }

}
