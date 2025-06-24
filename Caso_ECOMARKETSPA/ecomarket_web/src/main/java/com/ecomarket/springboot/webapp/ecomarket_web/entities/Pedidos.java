package com.ecomarket.springboot.webapp.ecomarket_web.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Pedidos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String gestion;
    private String historialPedido;
    private int fechaPedido;


    public Pedidos() {
        
    }


    public Pedidos(long id, String gestion, String historialPedido, int fechaPedido) {
        this.id = id;
        this.gestion = gestion;
        this.historialPedido = historialPedido;
        this.fechaPedido = fechaPedido;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getGestion() {
        return gestion;
    }


    public void setGestion(String gestion) {
        this.gestion = gestion;
    }


    public String getHistorialPedido() {
        return historialPedido;
    }


    public void setHistorialPedido(String historialPedido) {
        this.historialPedido = historialPedido;
    }


    public int getFechaPedido() {
        return fechaPedido;
    }


    public void setFechaPedido(int fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    




}