package com.agroflow.central.repository;

import com.agroflow.central.model.Cosecha;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CosechaRepository extends JpaRepository<Cosecha, UUID> {
}
