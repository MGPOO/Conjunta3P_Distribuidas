package com.agroflow.facturacion.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @Column(name = "factura_id", nullable = false, unique = true)
    private String facturaId;

    @Column(name = "cosecha_id", nullable = false)
    private String cosechaId;

    @Column(nullable = false)
    private Double monto;

    @Column(nullable = false)
    private Boolean pagado = false;

    @Column(nullable = false)
    private LocalDateTime fecha;

    public Factura() {}

    public Factura(String facturaId, String cosechaId, Double monto) {
        this.facturaId = facturaId;
        this.cosechaId = cosechaId;
        this.monto = monto;
        this.pagado = false;
        this.fecha = LocalDateTime.now();
    }

    // Getters y setters
    public String getFacturaId() { return facturaId; }
    public void setFacturaId(String facturaId) { this.facturaId = facturaId; }

    public String getCosechaId() { return cosechaId; }
    public void setCosechaId(String cosechaId) { this.cosechaId = cosechaId; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public Boolean getPagado() { return pagado; }
    public void setPagado(Boolean pagado) { this.pagado = pagado; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }
}
