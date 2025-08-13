package com.agroflow.central.repository;

import com.agroflow.central.model.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AgricultorRepository extends JpaRepository<Agricultor, UUID> {
}
