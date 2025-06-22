package com.ecomarket.springboot.webapp.ecomarket_web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Reporte;
import com.ecomarket.springboot.webapp.ecomarket_web.repository.ReporteRepository;

@Service
public class ReporteServiceImpl implements ReporteService{


    @Autowired
    private ReporteRepository repository;

    @Override
    @Transactional(readOnly = true)
    public List<Reporte> findByAll() {
        return (List<Reporte>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reporte> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Reporte save(Reporte unReporte) {
        return repository.save(unReporte);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id); 
    }
 }