package com.ecomarket.springboot.webapp.ecomarket_web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Pedidos;
import com.ecomarket.springboot.webapp.ecomarket_web.services.PedidoService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/pedidos")
@Tag(name = "Pedidos", description = "Operaciones relacionadas con los pedidos del sistema")
public class PedidoRestController {

    @Autowired
    private PedidoService pedidoService;

    @Operation(
        summary = "Listar todos los pedidos",
        description = "Obtiene todos los registros de pedidos existentes"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de pedidos obtenida correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))
    )
    @GetMapping
    public List<Pedidos> mostrarPedidos() {
        return pedidoService.findByAll();
    }

    @Operation(
        summary = "Obtener un pedido por ID",
        description = "Devuelve el detalle de un pedido según su ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Pedido encontrado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Pedido no encontrado"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> verPedido(@PathVariable Long id) {
        Optional<Pedidos> pedidoOptional = pedidoService.findById(id);
        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(pedidoOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Crear un nuevo pedido",
        description = "Crea un nuevo registro de pedido con los datos proporcionados"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Pedido creado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))
    )
    @PostMapping
    public ResponseEntity<Pedidos> crearPedido(@RequestBody Pedidos pedido) {
        Pedidos nuevoPedido = pedidoService.save(pedido);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoPedido);
    }

    @Operation(
        summary = "Modificar un pedido existente",
        description = "Actualiza los datos de un pedido si existe"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Pedido modificado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Pedido no encontrado"
    )
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

    @Operation(
        summary = "Eliminar un pedido por ID",
        description = "Elimina un pedido existente y devuelve el objeto eliminado"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Pedido eliminado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedidos.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Pedido no encontrado"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        Optional<Pedidos> eliminado = pedidoService.delete(id);
        if (eliminado.isPresent()) {
            return ResponseEntity.ok(eliminado.get());
        }
        return ResponseEntity.notFound().build();
    }
}
