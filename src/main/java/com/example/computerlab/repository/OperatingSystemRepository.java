package com.example.computerlab.repository;

import com.example.computerlab.model.OperatingSystem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatingSystemRepository extends JpaRepository<OperatingSystem, Long> {
}