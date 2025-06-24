package com.ecomarket.springboot.webapp.ecomarket_web.restcontrollers;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot.webapp.ecomarket_web.controllers.PedidoRestController;
import com.ecomarket.springboot.webapp.ecomarket_web.entities.Pedidos;
import com.ecomarket.springboot.webapp.ecomarket_web.services.PedidoService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(PedidoRestController.class)
public class PedidosRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private PedidoService pedidoService;

    @Test
    public void listarPedidosTest() throws Exception {
    when(pedidoService.findByAll()).thenReturn(List.of());

    mockMvc.perform(get("/api/pedidos")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
}



    @Test 
    public void verPedidoExistenteTest() throws Exception {
    Pedidos pedido = new Pedidos(1L, "Pedido prueba", "Historial prueba", 20250622);
    when(pedidoService.findById(1L)).thenReturn(Optional.of(pedido));

    mockMvc.perform(get("/api/pedidos/1")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
}


    @Test
    public void verReporteNoExistenteTest() throws Exception {
        when(pedidoService.findById(99L)).thenReturn(Optional.empty());
        mockMvc.perform(get("/api/pedidos/99")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearPedidoTest() throws Exception {
    Pedidos nuevoPedido = new Pedidos(0L, "Pedido prueba", "Historial prueba", 20250622);
    Pedidos pedidoCreado = new Pedidos(1L, "Pedido prueba", "Historial prueba", 20250622);

    when(pedidoService.save(any(Pedidos.class))).thenReturn(pedidoCreado);

    mockMvc.perform(post("/api/pedidos")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(nuevoPedido)))
            .andExpect(status().isCreated());
}

}