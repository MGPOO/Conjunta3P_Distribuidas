package com.agroflow.inventario.repository;

import com.agroflow.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductoRepository extends JpaRepository<Producto, UUID> {

    // Método para buscar por nombre del producto
    Optional<Producto> findByNombre(String nombre);
}
