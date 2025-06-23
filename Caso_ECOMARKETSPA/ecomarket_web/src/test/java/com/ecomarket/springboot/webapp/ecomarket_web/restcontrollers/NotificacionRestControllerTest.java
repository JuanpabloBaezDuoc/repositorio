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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    void listarNotificacionesTest() throws Exception {
        List<Notificacion> lista = List.of(
            new Notificacion(1L, "Alerta", "cliente1", "Mensaje 1", 20240623, "ENVIADA"),
            new Notificacion(2L, "Aviso", "cliente2", "Mensaje 2", 20240624, "ENVIADA")
        );

        when(notificacionService.findByAll()).thenReturn(lista);

        mockMvc.perform(get("/api/notificacion"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.size()").value(lista.size()));
    }

    @Test
    void obtenerNotificacionPorIdTest() throws Exception {
        Notificacion n = new Notificacion(1L, "Aviso", "cliente", "Mensaje", 20240624, "ENVIADA");

        when(notificacionService.findById(1L)).thenReturn(Optional.of(n));

        mockMvc.perform(get("/api/notificacion/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.tipo").value("Aviso"));
    }

    @Test
    void crearNotificacionTest() throws Exception {
        Notificacion entrada = new Notificacion(1L, "Info", "clienteX", "Contenido", 20240625, "ENVIADA");
        Notificacion salida = new Notificacion(10L, "Info", "clienteX", "Contenido", 20240625, "ENVIADA");

        when(notificacionService.save(any())).thenReturn(salida);

        mockMvc.perform(post("/api/notificacion")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(entrada)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(10L));
    }
@Test
    void actualizarNotificacionTest() throws Exception {
        Notificacion existente = new Notificacion(3L, "Info", "cliente3", "Viejo", 20240620, "PENDIENTE");
        Notificacion modificada = new Notificacion(3L, "Alerta", "cliente3", "Nuevo", 20240626, "ENVIADA");

        when(notificacionService.findById(3L)).thenReturn(Optional.of(existente));
        when(notificacionService.save(any())).thenReturn(modificada);

        mockMvc.perform(put("/api/notificacion/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(modificada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mensaje").value("Nuevo"));
    }

    @Test
    void eliminarNotificacionTest() throws Exception {
        Notificacion existente = new Notificacion(4L, "Info", "cliente4", "Mensaje", 20240622, "ENVIADA");

        when(notificacionService.findById(4L)).thenReturn(Optional.of(existente));
        doNothing().when(notificacionService).deleteById(4L);

        mockMvc.perform(delete("/api/notificacion/4"))
                .andExpect(status().isOk());
    }
}


