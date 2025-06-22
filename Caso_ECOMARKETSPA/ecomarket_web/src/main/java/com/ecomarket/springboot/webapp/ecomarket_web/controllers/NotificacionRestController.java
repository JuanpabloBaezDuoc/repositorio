package com.ecomarket.springboot.webapp.ecomarket_web.controllers;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Notificacion;
import com.ecomarket.springboot.webapp.ecomarket_web.services.NotificacionService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("api/notificacion")
public class NotificacionRestController {

    @Autowired
    private NotificacionService service;

    @GetMapping
    public List<Notificacion> List(){
        return service.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Notificacion> notificacionOptional = service.findById(id);
        if (notificacionOptional.isPresent()){
            return ResponseEntity.ok(notificacionOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear (@RequestBody Notificacion unnNotificacion){
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(unnNotificacion));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Notificacion unnNotificacion){
        Optional <Notificacion> notificacionOptional = service.findById(id);
        if(notificacionOptional.isPresent()){
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
