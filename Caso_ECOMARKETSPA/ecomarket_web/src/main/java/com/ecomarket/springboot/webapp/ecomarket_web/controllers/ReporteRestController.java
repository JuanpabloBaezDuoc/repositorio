package com.ecomarket.springboot.webapp.ecomarket_web.controllers;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Reporte;
import com.ecomarket.springboot.webapp.ecomarket_web.services.ReporteService;

@RestController
@RequestMapping("api/reportes")
public class ReporteRestController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public List<Reporte> mostrarReportes() {
        return reporteService.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verReporte(@PathVariable Long id) {
        Optional<Reporte> optionalReporte = reporteService.findById(id);
        if (optionalReporte.isPresent()) {
            return ResponseEntity.ok(optionalReporte.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Reporte> crearReporte(@RequestBody Reporte unReporte) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reporteService.save(unReporte));
    }

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
