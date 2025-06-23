package com.ecomarket.springboot.webapp.ecomarket_web.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Pedidos;
import com.ecomarket.springboot.webapp.ecomarket_web.services.PedidoService;

@RestController
@RequestMapping("api/pedidos")
public class PedidoRestController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedidos> mostrarPedidos() {
        return pedidoService.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verPedido(@PathVariable Long id) {
        Optional<Pedidos> pedidoOptional = pedidoService.findById(id);
        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(pedidoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pedidos> crearPedido(@RequestBody Pedidos pedido) {
        Pedidos nuevoPedido = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> modificarPedido(@PathVariable Long id, @RequestBody Pedidos pedido) {
        Optional<Pedidos> pedidoOptional = pedidoService.findById(id);
        if (pedidoOptional.isPresent()) {
            Pedidos pedidoExistente = pedidoOptional.get();
            pedidoExistente.setGestion(pedido.getGestion());
            pedidoExistente.setHistorialPedido(pedido.getHistorialPedido());
            pedidoExistente.setFechaPedido(pedido.getFechaPedido());

            Pedidos pedidoModificado = pedidoService.save(pedidoExistente);
            return ResponseEntity.ok(pedidoModificado);
        }
        return ResponseEntity.notFound().build();
    }
}
