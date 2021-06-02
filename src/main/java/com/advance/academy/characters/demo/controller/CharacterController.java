package com.advance.academy.characters.demo.controller;

import com.advance.academy.characters.demo.dto.CharacterDto;
import com.advance.academy.characters.demo.model.Character;
import com.advance.academy.characters.demo.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "/characters")
public class CharacterController {

    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping
    public ResponseEntity<Set<CharacterDto>> findAll() {
        return ResponseEntity.ok(characterService.findAll());
    }

    @GetMapping(value = "/template")
    public CharacterDto getTemplate() {
        return characterService.getTemplate();
    }

    @GetMapping(value = "/name/{name}")
    public ResponseEntity<CharacterDto> findByName(@PathVariable(value = "name") String name) {
        return ResponseEntity.ok(characterService.findByName(name));
    }

    @GetMapping(value = "/id/{id}")
    public ResponseEntity<CharacterDto> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.ok(characterService.findById(id));
    }

    @PostMapping
    public void create(@RequestBody CharacterDto characterDto) {
        characterService.create(characterDto);
    }

    @PutMapping(value = "/name/{name}")
    public ResponseEntity<Character> updateByName(@PathVariable(value = "name") String name, @RequestBody CharacterDto characterDto) {
        Character updatedCharacter = characterService.updateByName(name, characterDto);

        return ResponseEntity.ok(updatedCharacter);
    }

    @PutMapping(value = "/id/{id}")
    public ResponseEntity<Character> updateById(@PathVariable(value = "id") Long id, @RequestBody CharacterDto characterDto) {
        Character updatedCharacter = characterService.updateById(id, characterDto);

        return ResponseEntity.ok(updatedCharacter);
    }

    @DeleteMapping(value = "/name/{name}")
    public ResponseEntity<HttpStatus> deleteByName(@PathVariable(value = "name") String name) {
        characterService.deleteByName(name);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "/id/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable(value = "id") Long id) {
        characterService.deleteById(id);

        return ResponseEntity.ok().build();
    }

}
