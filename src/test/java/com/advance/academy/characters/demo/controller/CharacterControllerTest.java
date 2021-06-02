package com.advance.academy.characters.demo.controller;

import com.advance.academy.characters.demo.dto.CharacterDto;
import com.advance.academy.characters.demo.model.Character;
import com.advance.academy.characters.demo.model.Weapon;
import com.advance.academy.characters.demo.repository.DefaultEquipmentRepository;
import com.advance.academy.characters.demo.service.CharacterService;
import com.advance.academy.characters.demo.service.ItemService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@AutoConfigureMockMvc
@WebMvcTest(controllers = CharacterController.class)
class CharacterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CharacterService characterService;

    @MockBean
    private ItemService itemService;

    @MockBean
    private DefaultEquipmentRepository defaultEquipmentRepository;

    @Test
    public void verifyFindByNameWorks() throws Exception {
        String expectedName = "Thrall";

        when(characterService.findByName(expectedName))
                .thenReturn(CharacterDto.builder().name(expectedName).build());

        mockMvc.perform(
                get("/characters/name/" + expectedName)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));

        verify(characterService, times(1))
                .findByName(expectedName);
    }

    @Test
    public void verifyFindByIdWorks() throws Exception {
        Long expectedId = 10L;

        when(characterService.findById(expectedId))
                .thenReturn(CharacterDto.builder().id(expectedId).build());

        mockMvc.perform(
                get("/characters/id/" + expectedId)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));

        verify(characterService, times(1))
                .findById(expectedId);
    }

}