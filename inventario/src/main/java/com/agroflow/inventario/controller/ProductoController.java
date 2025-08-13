package com.agroflow.inventario.controller;

import com.agroflow.inventario.model.Producto;
import com.agroflow.inventario.repository.ProductoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final ProductoRepository repository;

    public ProductoController(ProductoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Producto> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Producto> create(@RequestBody Producto p) {
        return ResponseEntity.ok(repository.save(p));
    }

    @PutMapping("/{id}/descontar")
    public ResponseEntity<?> descontar(@PathVariable UUID id, @RequestParam int cantidad) {
        return repository.findById(id)
                .map(p -> {
                    if (p.getCantidad() < cantidad) return ResponseEntity.badRequest().body("Stock insuficiente");
                    p.setCantidad(p.getCantidad() - cantidad);
                    return ResponseEntity.ok(repository.save(p));
                }).orElse(ResponseEntity.notFound().build());
    }
}
