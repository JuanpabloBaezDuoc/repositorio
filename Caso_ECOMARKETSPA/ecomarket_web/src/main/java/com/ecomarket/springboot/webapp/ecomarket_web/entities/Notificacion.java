package com.ecomarket.springboot.webapp.ecomarket_web.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table (name = "notificacion")
public class Notificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tipo;
    private String destinatario;
    private String mensaje;
    private int fecha_envio;
    private String estado;

    public Notificacion() {

    }

    public Notificacion(long id, String tipo, String destinario, String mensaje, int fecha_envio, String estado) {
        this.id = id;
        this.tipo = tipo;
        this.destinatario = destinario;
        this.mensaje = mensaje;
        this.fecha_envio = fecha_envio;
        this.estado = estado;
    }


    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }


    public String getTipo() {
        return tipo;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getDestinario() {
        return destinatario;
    }


    public void setDestinario(String destinatario) {
        this.destinatario = destinatario;
    }


    public String getMensaje() {
        return mensaje;
    }


    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }


    public int getFecha_envio() {
        return fecha_envio;
    }


    public void setFecha_envio(int fecha_envio) {
        this.fecha_envio = fecha_envio;
    }


    public String getEstado() {
        return estado;
    }


    public void setEstado(String estado) {
        this.estado = estado;
    }    



}
