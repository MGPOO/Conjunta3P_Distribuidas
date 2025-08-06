package com.agroflow.facturacion.repository;

import com.agroflow.facturacion.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository extends JpaRepository<Factura, String> {
}
