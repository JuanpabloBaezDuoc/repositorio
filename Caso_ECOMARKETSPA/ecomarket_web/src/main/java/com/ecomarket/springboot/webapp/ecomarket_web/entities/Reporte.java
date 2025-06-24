package com.ecomarket.springboot.webapp.ecomarket_web.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String descripcion;
    private int fechaGeneracion;

    public Reporte(){
    }
    
    public Reporte(Long id, String descripcion, int fechaGeneracion){
        this.id=id;
        this.descripcion=descripcion;
        this.fechaGeneracion=fechaGeneracion;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getdescripcion(){
        return descripcion;
    }

    public void setdescripcion(String descripcion){
        this.descripcion = descripcion;
    }

    public int getfechaGeneracion(){
        return fechaGeneracion;
    }

    public void setfechaGeneracion(int fechaGeneracion){
        this.fechaGeneracion=fechaGeneracion;

    }
}