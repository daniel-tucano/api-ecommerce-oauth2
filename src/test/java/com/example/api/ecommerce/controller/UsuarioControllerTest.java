package com.example.api.ecommerce.controller;

import com.example.api.ecommerce.repository.UsuarioRepository;
import com.example.api.ecommerce.response.UsuarioResponse;
import com.example.api.ecommerce.model.Usuario;
import com.example.api.ecommerce.request.UsuarioRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@Transactional
class UsuarioControllerTest {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    private void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void deveRegistrarUmUsuarioComSucesso() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                objectMapper.writeValueAsString(new UsuarioRequest("teste@email.com"))
                        )
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(new UsuarioResponse(1L, "teste@email.com"))));
    }

    @Test
    public void deveFalharAoTentarRegistrarEmailMalFormatado() throws Exception {
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/usuario")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(new UsuarioRequest("testeemail.com"))
                                )
                )
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void deveObterUsuarioAPartirDoEmailComSucesso() throws Exception {
        usuarioRepository.save(new Usuario("teste@email.com"));

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/usuario/teste@email.com")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(new UsuarioResponse(1L, "teste@email.com"))));
    }

}