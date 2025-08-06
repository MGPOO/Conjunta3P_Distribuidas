package com.agroflow.facturacion.controller;

import com.agroflow.facturacion.model.Factura;
import com.agroflow.facturacion.service.FacturaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    private final FacturaService facturaService;

    public FacturaController(FacturaService facturaService) {
        this.facturaService = facturaService;
    }

    @PostMapping
    public ResponseEntity<Factura> generarFactura(@RequestBody Map<String, Object> payload) {
        String cosechaId = (String) payload.get("cosecha_id");
        String producto = (String) payload.get("producto");
        double toneladas = Double.parseDouble(payload.get("toneladas").toString());

        Factura factura = facturaService.crearFactura(cosechaId, producto, toneladas);
        return ResponseEntity.ok(factura);
    }
}
