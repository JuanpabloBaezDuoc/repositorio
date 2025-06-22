package com.ecomarket.springboot.webapp.ecomarket_web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Usuario;
import com.ecomarket.springboot.webapp.ecomarket_web.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService{


    @Autowired
    private UsuarioRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findByAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuario> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Usuario save(Usuario unUsuario) {
        return repository.save(unUsuario);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id); 
    }
}