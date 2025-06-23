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
    private ReporteRepository reporterepositories ;

    @Override
    @Transactional(readOnly = true)
    public List<Reporte> findByAll() {
        return (List<Reporte>) reporterepositories.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Reporte> findById(Long id) {
        return reporterepositories.findById(id);
    }

    @Override
    @Transactional
    public Reporte save(Reporte unReporte) {
        return reporterepositories.save(unReporte);
    }

    @Override
    @Transactional
    public Optional<Reporte> delete(Long id) {
    Optional<Reporte> reporteOptional = reporterepositories.findById(id);
    reporteOptional.ifPresent(reporte -> reporterepositories.deleteById(id));
    return reporteOptional;
}

}
