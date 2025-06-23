package com.ecomarket.springboot.webapp.ecomarket_web.restcontrollers;

import com.ecomarket.springboot.webapp.ecomarket_web.controllers.NotificacionRestController;
import com.ecomarket.springboot.webapp.ecomarket_web.entities.Notificacion;
import com.ecomarket.springboot.webapp.ecomarket_web.services.NotificacionService;
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

@WebMvcTest(NotificacionRestController.class)
public class NotificacionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private NotificacionService notificacionService;

    @Test
    public void listarNotificacionesTest() throws Exception {
        when(notificacionService.findByAll()).thenReturn(List.of());

        mockMvc.perform(get("/api/notificaciones")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void obtenerNotificacionPorIdTest() throws Exception {
        Notificacion n = new Notificacion(1L, "ALERTA", "usuario@dominio.cl", "Mensaje", 20240621, "ENVIADO");
        when(notificacionService.findById(1L)).thenReturn(Optional.of(n));

        mockMvc.perform(get("/api/notificaciones/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void notificacionNoExisteTest() throws Exception {
        when(notificacionService.findById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/notificaciones/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearNotificacionTest() throws Exception {
        Notificacion nueva = new Notificacion(0L, "INFO", "admin@empresa.com", "Nueva política", 20240622, "NUEVA");
        Notificacion guardada = new Notificacion(1L, "INFO", "admin@empresa.com", "Nueva política", 20240622, "NUEVA");

        when(notificacionService.save(any(Notificacion.class))).thenReturn(guardada);

        mockMvc.perform(post("/api/notificaciones")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isCreated());
    }

    @Test
    public void actualizarNotificacionTest() throws Exception {
        Notificacion existente = new Notificacion(1L, "INFO", "destino", "original", 20240101, "NUEVA");
        Notificacion modificada = new Notificacion(1L, "URGENTE", "nuevo@correo.cl", "modificado", 20240623, "ENVIADO");

        when(notificacionService.findById(1L)).thenReturn(Optional.of(existente));
        when(notificacionService.save(any(Notificacion.class))).thenReturn(modificada);

        mockMvc.perform(put("/api/notificaciones/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modificada)))
                .andExpect(status().isOk());
    }

    @Test
    public void eliminarNotificacionTest() throws Exception {
        when(notificacionService.findById(1L)).thenReturn(Optional.of(new Notificacion()));
        doNothing().when(notificacionService).deleteById(1L);

        mockMvc.perform(delete("/api/notificaciones/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    public void eliminarNotificacionNoExistenteTest() throws Exception {
        when(notificacionService.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(delete("/api/notificaciones/999"))
                .andExpect(status().isNotFound());
    }
}


