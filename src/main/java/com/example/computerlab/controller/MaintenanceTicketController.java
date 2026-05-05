package com.example.computerlab.controller;

import com.example.computerlab.model.MaintenanceTicket;
import com.example.computerlab.service.MaintenanceTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/maintenance-tickets")
@RequiredArgsConstructor
public class MaintenanceTicketController {

    private final MaintenanceTicketService maintenanceTicketService;

    @GetMapping
    public List<MaintenanceTicket> getAllMaintenanceTickets() {
        return maintenanceTicketService.getAllMaintenanceTickets();
    }

    @GetMapping("/{id}")
    public MaintenanceTicket getMaintenanceTicketById(@PathVariable Long id) {
        return maintenanceTicketService.getMaintenanceTicketById(id);
    }

    @PostMapping
    public MaintenanceTicket createMaintenanceTicket(@RequestBody MaintenanceTicket maintenanceTicket) {
        return maintenanceTicketService.createMaintenanceTicket(maintenanceTicket);
    }

    @PutMapping("/{id}")
    public MaintenanceTicket updateMaintenanceTicket(@PathVariable Long id,
                                                     @RequestBody MaintenanceTicket updatedTicket) {
        return maintenanceTicketService.updateMaintenanceTicket(id, updatedTicket);
    }

    @DeleteMapping("/{id}")
    public String deleteMaintenanceTicket(@PathVariable Long id) {
        maintenanceTicketService.deleteMaintenanceTicket(id);
        return "Maintenance ticket deleted successfully";
    }
}