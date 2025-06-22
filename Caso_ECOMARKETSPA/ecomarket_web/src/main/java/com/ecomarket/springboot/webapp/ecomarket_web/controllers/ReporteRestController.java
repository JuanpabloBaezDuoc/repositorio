package com.ecomarket.springboot.webapp.ecomarket_web.controllers;


import com.ecomarket.springboot.webapp.ecomarket_web.entities.Reporte;
import com.ecomarket.springboot.webapp.ecomarket_web.repository.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/reportes")
public class ReporteRestController {

    @Autowired
    private ReporteRepository service;

    @GetMapping
    public List<Reporte> List() {
        return service.findByAll();
    }

  @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Reporte> reporteOptional = service.findById(id);
        if (reporteOptional.isPresent()){
            return ResponseEntity.ok(reporteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

@PostMapping
    public Reporte createReporte(@RequestBody Reporte reporte) {
        return service.save(reporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reporte> updateReporte(@PathVariable Long id, @RequestBody Reporte reporteDetails) {
        Optional<Reporte> reporteOptional = service.findById(id);
        if (reporteOptional.isPresent()) {
            Reporte reporte = reporteOptional.get();
            reporte.setdescripcion(reporteDetails.getdescripcion());
            reporte.setfechaGeneracion(reporteDetails.getfechaGeneracion());
            return ResponseEntity.ok(service.save(reporte));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReporte(@PathVariable Long id) {
        if (service.existsById(id)) {
            service.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}


