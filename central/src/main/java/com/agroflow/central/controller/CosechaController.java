package com.agroflow.central.controller;

import com.agroflow.central.messaging.RabbitMQPublisher;
import com.agroflow.central.model.Cosecha;
import com.agroflow.central.repository.CosechaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/cosechas")
public class CosechaController {

    private final CosechaRepository repository;
    private final RabbitMQPublisher publisher;

    public CosechaController(CosechaRepository repository, RabbitMQPublisher publisher) {
        this.repository = repository;
        this.publisher = publisher;
    }

    @GetMapping
    public List<Cosecha> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Cosecha> create(@RequestBody Cosecha c) {
        Cosecha nueva = repository.save(c);
        publisher.publicarNuevaCosecha(nueva); // <- aquí se publica el evento
        return ResponseEntity.ok(nueva);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Cosecha> actualizarEstado(@PathVariable UUID id, @RequestBody Map<String, String> body) {
        return repository.findById(id)
                .map(c -> {
                    c.setEstado(body.get("estado"));
                    c.setFacturaId(body.get("factura_id"));
                    return ResponseEntity.ok(repository.save(c));
                }).orElse(ResponseEntity.notFound().build());
    }
}
