package com.ecomarket.springboot.webapp.ecomarket_web.services;
import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Notificacion;

public interface NotificacionService {
    List<Notificacion> findByAll();

    Optional<Notificacion> findById(Long id);

    Notificacion save(Notificacion unnNotificacion);

    void deleteById(Long id);   

}