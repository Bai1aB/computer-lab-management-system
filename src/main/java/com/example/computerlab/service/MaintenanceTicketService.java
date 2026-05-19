package com.example.computerlab.service;

import com.example.computerlab.enums.ComputerStatus;
import com.example.computerlab.enums.TicketStatus;
import com.example.computerlab.model.Computer;
import com.example.computerlab.model.MaintenanceTicket;
import com.example.computerlab.repository.ComputerRepository;
import com.example.computerlab.repository.MaintenanceTicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceTicketService {

    private final MaintenanceTicketRepository maintenanceTicketRepository;
    private final ComputerRepository computerRepository;

    public List<MaintenanceTicket> getAllMaintenanceTickets() {
        return maintenanceTicketRepository.findAll();
    }

    public MaintenanceTicket getMaintenanceTicketById(Long id) {
        return maintenanceTicketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Maintenance ticket not found with id: " + id));
    }

    public MaintenanceTicket createMaintenanceTicket(MaintenanceTicket maintenanceTicket) {
        Long computerId = maintenanceTicket.getComputer().getId();

        Computer computer = computerRepository.findById(computerId)
                .orElseThrow(() -> new RuntimeException("Computer not found with id: " + computerId));

        maintenanceTicket.setComputer(computer);

        if (maintenanceTicket.getStatus() == null) {
            maintenanceTicket.setStatus(TicketStatus.OPEN);
        }

        if (maintenanceTicket.getCreatedAt() == null) {
            maintenanceTicket.setCreatedAt(LocalDateTime.now());
        }

        computer.setStatus(ComputerStatus.MAINTENANCE);
        computerRepository.save(computer);

        return maintenanceTicketRepository.save(maintenanceTicket);
    }

    public MaintenanceTicket updateMaintenanceTicket(Long id, MaintenanceTicket updatedTicket) {
        MaintenanceTicket existingTicket = getMaintenanceTicketById(id);

        existingTicket.setDescription(updatedTicket.getDescription());
        existingTicket.setPriority(updatedTicket.getPriority());
        existingTicket.setStatus(updatedTicket.getStatus());

        return maintenanceTicketRepository.save(existingTicket);
    }

    public MaintenanceTicket updateTicketStatus(Long id, TicketStatus status) {
        MaintenanceTicket existingTicket = getMaintenanceTicketById(id);
        existingTicket.setStatus(status);
        return maintenanceTicketRepository.save(existingTicket);
    }

    public void deleteMaintenanceTicket(Long id) {
        MaintenanceTicket existingTicket = getMaintenanceTicketById(id);
        maintenanceTicketRepository.delete(existingTicket);
    }
}
