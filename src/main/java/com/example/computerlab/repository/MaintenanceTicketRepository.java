package com.example.computerlab.repository;

import com.example.computerlab.model.MaintenanceTicket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceTicketRepository extends JpaRepository<MaintenanceTicket, Long> {
}