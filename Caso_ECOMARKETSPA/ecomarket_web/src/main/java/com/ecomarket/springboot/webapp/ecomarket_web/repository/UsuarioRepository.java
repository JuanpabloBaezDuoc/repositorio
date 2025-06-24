package com.ecomarket.springboot.webapp.ecomarket_web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    @Query("SELECT u FROM Usuario u") // Consulta personalizada que devuelve todos los usuarios
    List<Usuario> findByAll(); 

}