package com.ecomarket.springboot.webapp.ecomarket_web.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Usuario;

public interface UsuarioRepository extends CrudRepository <Usuario, Long> {

    List<Usuario> findByAll();

    
}
