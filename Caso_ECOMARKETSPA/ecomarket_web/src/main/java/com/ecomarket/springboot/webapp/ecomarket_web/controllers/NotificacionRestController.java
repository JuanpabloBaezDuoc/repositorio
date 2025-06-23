package com.ecomarket.springboot.webapp.ecomarket_web.controllers;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Notificacion;
import com.ecomarket.springboot.webapp.ecomarket_web.services.NotificacionService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/notificacion")
@Tag(name = "Notificación", description = "Operaciones CRUD sobre notificaciones")
public class NotificacionRestController {

    @Autowired
    private NotificacionService service;

    @Operation(
        summary = "Listar notificaciones",
        description = "Retorna una lista con todas las notificaciones registradas"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Listado obtenido correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class))
    )
    @GetMapping
    public List<Notificacion> List() {
        return service.findByAll();
    }

    @Operation(
        summary = "Obtener notificación por ID",
        description = "Retorna una notificación específica si el ID existe"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Notificación encontrada",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class))
    )
    @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Notificacion> notificacionOptional = service.findById(id);
        if (notificacionOptional.isPresent()) {
            return ResponseEntity.ok(notificacionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Crear nueva notificación",
        description = "Crea y guarda una nueva notificación en el sistema"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Notificación creada correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Notificacion.class))
    )
    @PostMapping
    public ResponseEntity<Notificacion> crear(@RequestBody Notificacion unnNotificacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unnNotificacion));
    }

    @Operation(
        summary = "Modificar una notificación",
        description = "Modifica una notificación existente según el ID"
    )
    @ApiResponse(responseCode = "200", description = "Notificación modificada correctamente")
    @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Notificacion unnNotificacion) {
        Optional<Notificacion> notificacionOptional = service.findById(id);
        if (notificacionOptional.isPresent()) {
            Notificacion notificacionexistente = notificacionOptional.get();
            notificacionexistente.setTipo(unnNotificacion.getTipo());
            notificacionexistente.setDestinario(unnNotificacion.getDestinario());
            notificacionexistente.setMensaje(unnNotificacion.getMensaje());
            notificacionexistente.setFecha_envio(unnNotificacion.getFecha_envio());
            notificacionexistente.setEstado(unnNotificacion.getEstado());
            Notificacion notificacionmodificado = service.save(notificacionexistente);
            return ResponseEntity.ok(notificacionmodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar notificación",
        description = "Elimina una notificación específica por su ID"
    )
    @ApiResponse(responseCode = "200", description = "Notificación eliminada correctamente")
    @ApiResponse(responseCode = "404", description = "Notificación no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Notificacion> notificacionOptional = service.findById(id);
        if (notificacionOptional.isPresent()) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
