package com.agroflow.central.controller;

import com.agroflow.central.model.Agricultor;
import com.agroflow.central.repository.AgricultorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/agricultores")
public class AgricultorController {

    private final AgricultorRepository repository;

    public AgricultorController(AgricultorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Agricultor> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Agricultor> create(@RequestBody Agricultor a) {
        Agricultor nuevo = repository.save(a);
        return ResponseEntity.ok(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agricultor> getOne(@PathVariable UUID id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agricultor> update(@PathVariable UUID id, @RequestBody Agricultor updated) {
        return repository.findById(id)
                .map(a -> {
                    a.setNombre(updated.getNombre());
                    a.setCorreo(updated.getCorreo());
                    a.setTelefono(updated.getTelefono());
                    return ResponseEntity.ok(repository.save(a));
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
