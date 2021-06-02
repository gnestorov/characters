package com.advance.academy.characters.demo.controller;

import com.advance.academy.characters.demo.model.Weapon;
import com.advance.academy.characters.demo.repository.WeaponRepository;
import com.advance.academy.characters.demo.service.ItemService;
import org.junit.jupiter.api.Test;
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
@WebMvcTest(controllers = ItemController.class)
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ItemService itemService;

    @MockBean
    private WeaponRepository weaponRepository;

    // Work in progress
    @Test
    public void verifyFindByIdWorks() throws Exception {
        Long expectedId = 10L;

        when(itemService.findById(expectedId))
                .thenReturn(Weapon.builder().id(expectedId).build());

        mockMvc.perform(
                get("/items/" + expectedId)
                        .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE));

        verify(itemService, times(1))
                .findById(expectedId);
    }

}