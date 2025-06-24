package com.ecomarket.springboot.webapp.ecomarket_web.services;

import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Usuario;

public interface UsuarioService {
    List <Usuario> findByAll();

    Optional<Usuario> findById(Long id);

    Usuario save(Usuario unUsuario);

    void deleteById(Long id);

}