package com.ecomarket.springboot.webapp.ecomarket_web.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Notificacion;
import com.ecomarket.springboot.webapp.ecomarket_web.entities.Reporte;

public interface ReporteRepository extends CrudRepository<Reporte, Long>{

    List<Reporte> findByAll();

    Optional<Notificacion> findById();


}
