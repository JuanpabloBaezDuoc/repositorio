package com.ecomarket.springboot.webapp.ecomarket_web.restcontrollers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot.webapp.ecomarket_web.controllers.ReporteRestController;
import com.ecomarket.springboot.webapp.ecomarket_web.entities.Reporte;
import com.ecomarket.springboot.webapp.ecomarket_web.services.ReporteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ReporteRestController.class)
public class ReporteRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ReporteService reporteService;

    @Test
    public void listarReportesTest() throws Exception {
        when(reporteService.findByAll()).thenReturn(List.of());
        mockMvc.perform(get("/api/reportes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verReporteExistenteTest() throws Exception {
        Reporte reporte = new Reporte(1L, "Reporte prueba", 20250622);
        when(reporteService.findById(1L)).thenReturn(Optional.of(reporte));
        mockMvc.perform(get("/api/reportes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void verReporteNoExistenteTest() throws Exception {
        when(reporteService.findById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/reportes/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearReporteTest() throws Exception {
        Reporte nuevoReporte = new Reporte(null, "Nuevo reporte", 20250622);
        Reporte reporteCreado = new Reporte(1L, "Nuevo reporte", 20250622);
        when(reporteService.save(any(Reporte.class))).thenReturn(reporteCreado);

        mockMvc.perform(post("/api/reportes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(nuevoReporte)))
                .andExpect(status().isCreated());
    }
}