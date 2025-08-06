package com.agroflow.facturacion.service;

import com.agroflow.facturacion.model.Factura;
import com.agroflow.facturacion.repository.FacturaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    public Factura crearFactura(String cosechaId, String producto, double toneladas) {
        double monto = calcularMonto(producto, toneladas);
        String uuid = UUID.randomUUID().toString();

        Factura factura = new Factura(uuid, cosechaId, monto);
        return facturaRepository.save(factura);
    }

    private double calcularMonto(String producto, double toneladas) {
        return switch (producto) {
            case "Arroz Oro" -> toneladas * 120;
            case "Café Premium" -> toneladas * 300;
            default -> throw new IllegalArgumentException("Producto no válido");
        };
    }
}
