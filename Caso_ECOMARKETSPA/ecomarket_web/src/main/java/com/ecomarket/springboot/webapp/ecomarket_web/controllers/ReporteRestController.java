package com.ecomarket.springboot.webapp.ecomarket_web.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Reporte;
import com.ecomarket.springboot.webapp.ecomarket_web.services.ReporteService;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@RestController
@RequestMapping("api/reportes")
@Tag(name = "Reportes", description = "Operaciones relacionadas con los reportes generados en el sistema")
public class ReporteRestController {

    @Autowired
    private ReporteService reporteService;

    @Operation(
        summary = "Listar todos los reportes",
        description = "Obtiene todos los registros de reportes existentes"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Lista de reportes obtenida correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reporte.class))
    )
    @GetMapping
    public List<Reporte> mostrarReportes() {
        return reporteService.findByAll();
    }

    @Operation(
        summary = "Obtener un reporte por ID",
        description = "Devuelve el detalle de un reporte según su ID"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Reporte encontrado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reporte.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Reporte no encontrado"
    )
    @GetMapping("/{id}")
    public ResponseEntity<?> verReporte(@PathVariable Long id) {
        Optional<Reporte> optionalReporte = reporteService.findById(id);
        if (optionalReporte.isPresent()) {
            return ResponseEntity.ok(optionalReporte.get());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Crear un nuevo reporte",
        description = "Crea un nuevo registro de reporte con los datos proporcionados"
    )
    @ApiResponse(
        responseCode = "201",
        description = "Reporte creado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reporte.class))
    )
    @PostMapping
    public ResponseEntity<Reporte> crearReporte(@RequestBody Reporte unReporte) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.save(unReporte));
    }

    @Operation(
        summary = "Modificar un reporte existente",
        description = "Actualiza los datos de un reporte si existe"
    )
    @ApiResponse(
        responseCode = "200",
        description = "Reporte modificado correctamente",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = Reporte.class))
    )
    @ApiResponse(
        responseCode = "404",
        description = "Reporte no encontrado"
    )
    @PutMapping("/{id}")
    public ResponseEntity<?> modificarReporte(@PathVariable Long id, @RequestBody Reporte unReporte) {
        Optional<Reporte> optionalReporte = reporteService.findById(id);
        if (optionalReporte.isPresent()) {
            Reporte reporteExiste = optionalReporte.get();
            reporteExiste.setdescripcion(unReporte.getdescripcion());
            reporteExiste.setfechaGeneracion(unReporte.getfechaGeneracion());
            Reporte reporteModificado = reporteService.save(reporteExiste);
            return ResponseEntity.ok(reporteModificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(
        summary = "Eliminar un reporte por ID",
        description = "Elimina un reporte existente del sistema si existe"
    )
    @ApiResponse(
        responseCode = "204",
        description = "Reporte eliminado correctamente"
    )
    @ApiResponse(
        responseCode = "404",
        description = "Reporte no encontrado"
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReporte(@PathVariable Long id) {
        Optional<Reporte> reporteOptional = reporteService.findById(id);
        if (reporteOptional.isPresent()) {
            reporteService.delete(id);
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.notFound().build();
    }
}
