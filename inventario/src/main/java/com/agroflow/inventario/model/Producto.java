package com.agroflow.inventario.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
public class Producto {

    @Id
    @GeneratedValue
    private UUID id;

    private String nombre;
    private Integer cantidad;

    // Getters y setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }
}
