package com.ecomarket.springboot.webapp.ecomarket_web.services;

import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Reporte;

public interface ReporteService {
    List<Reporte> findByAll();

    Optional<Reporte> findById(Long id);

    Reporte save(Reporte unReporte);

    void deleteById(Long id);   

    
}