package com.ecomarket.springboot.webapp.ecomarket_web.restcontrollers;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Usuario;
import com.ecomarket.springboot.webapp.ecomarket_web.repository.UsuarioRepository;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UsuarioRestControllerTest.class)
public class UsuarioRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UsuarioRepository usuarioRepository;

    @Test
    public void listarUsuariosTest() throws Exception {
        when(usuarioRepository.findByAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void obtenerUsuarioPorIdTest() throws Exception {
        Usuario usuario = new Usuario(1L, "Sofía", "Ramírez", "sofia@email.com", "987654321");
        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        mockMvc.perform(get("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void usuarioNoExisteTest() throws Exception {
        when(usuarioRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/usuarios/999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearUsuarioTest() throws Exception {
        Usuario nuevo = new Usuario(null, "Juan", "Pérez", "juan@email.com", "123456789");
        Usuario guardado = new Usuario(1L, "Juan", "Pérez", "juan@email.com", "123456789");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(guardado);

        mockMvc.perform(post("/api/usuarios")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevo)))
                .andExpect(status().isCreated());
    }

    @Test
    public void actualizarUsuarioTest() throws Exception {
        Usuario existente = new Usuario(1L, "Pedro", "López", "pedro@email.com", "22222222");
        Usuario modificado = new Usuario(1L, "Pedro", "López", "pedro_nuevo@email.com", "22222222");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(existente));
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(modificado);

        mockMvc.perform(put("/api/usuarios/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modificado)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarUsuarioTest() throws Exception {
        when(usuarioRepository.existsById(1L)).thenReturn(true);
        doNothing().when(usuarioRepository).deleteById(1L);

        mockMvc.perform(delete("/api/usuarios/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarUsuarioNoExistenteTest() throws Exception {
        when(usuarioRepository.existsById(99L)).thenReturn(false);

        mockMvc.perform(delete("/api/usuarios/99"))
                .andExpect(status().isNotFound());
    }
}
