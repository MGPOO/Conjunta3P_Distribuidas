package com.agroflow.central.model;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "agricultores")
public class Agricultor {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String correo;

    @Column(nullable = false)
    private String telefono;

    public Agricultor() {
        this.id = UUID.randomUUID();
    }

    public Agricultor(String nombre, String correo, String telefono) {
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
}
