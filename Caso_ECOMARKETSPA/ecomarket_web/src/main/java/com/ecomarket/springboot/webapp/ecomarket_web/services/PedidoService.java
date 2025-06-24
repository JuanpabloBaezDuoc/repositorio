package com.ecomarket.springboot.webapp.ecomarket_web.services;

import java.util.List;
import java.util.Optional;

import com.ecomarket.springboot.webapp.ecomarket_web.entities.Pedidos;


public interface PedidoService {

    List<Pedidos> findByAll();

    Optional<Pedidos> findById(Long id);

    Pedidos save(Pedidos unPedido);

    Optional<Pedidos> delete(Long id);


}