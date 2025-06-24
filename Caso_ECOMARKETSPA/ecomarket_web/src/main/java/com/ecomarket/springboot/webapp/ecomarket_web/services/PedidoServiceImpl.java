package com.ecomarket.springboot.webapp.ecomarket_web.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Pedidos;
import com.ecomarket.springboot.webapp.ecomarket_web.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidosrepositories;

    @Override
    @Transactional(readOnly = true)
    public List<Pedidos> findByAll() {
        return (List<Pedidos>) pedidosrepositories.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedidos> findById(Long id) {
        return pedidosrepositories.findById(id);
    }

    @Override
    @Transactional
    public Pedidos save(Pedidos unPedido) {
        return pedidosrepositories.save(unPedido);
    }

    @Override
    @Transactional
    public Optional<Pedidos> delete(Long id) {
        Optional<Pedidos> pedidoOptional = pedidosrepositories.findById(id);
        pedidoOptional.ifPresent(pedido -> pedidosrepositories.deleteById(id));
        return pedidoOptional;
    }
}
