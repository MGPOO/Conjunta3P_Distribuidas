package com.agroflow.central.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "cosechas")
public class Cosecha {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "agricultor_id", nullable = false)
    private UUID agricultorId;

    @Column(nullable = false)
    private String producto;

    @Column(nullable = false)
    private double toneladas;

    @Column(nullable = false)
    private String estado = "REGISTRADA";

    @Column(name = "factura_id")
    private String facturaId;

    @Column(nullable = false)
    private LocalDateTime fecha;

    public Cosecha() {
        this.id = UUID.randomUUID();
        this.fecha = LocalDateTime.now();
    }

    public Cosecha(UUID agricultorId, String producto, double toneladas) {
        this();
        this.agricultorId = agricultorId;
        this.producto = producto;
        this.toneladas = toneladas;
    }

    // Getters y Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getAgricultorId() { return agricultorId; }
    public void setAgricultorId(UUID agricultorId) { this.agricultorId = agricultorId; }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }

    public double getToneladas() { return toneladas; }
    public void setToneladas(double toneladas) { this.toneladas = toneladas; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getFacturaId() { return facturaId; }
    public void setFacturaId(String facturaId) { this.facturaId = facturaId; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
