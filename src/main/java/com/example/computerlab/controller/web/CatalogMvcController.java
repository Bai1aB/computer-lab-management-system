package com.example.computerlab.controller.web;

import com.example.computerlab.service.LabRoomService;
import com.example.computerlab.service.MaintenanceTicketService;
import com.example.computerlab.service.OperatingSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class CatalogMvcController {

    private final LabRoomService labRoomService;
    private final OperatingSystemService operatingSystemService;
    private final MaintenanceTicketService maintenanceTicketService;

    @GetMapping("/labrooms")
    public String labRooms(Model model) {
        model.addAttribute("activePage", "labrooms");
        model.addAttribute("labRooms", labRoomService.getAllLabRooms());
        return "labrooms/list";
    }

    @GetMapping("/operating-systems")
    public String operatingSystems(Model model) {
        model.addAttribute("activePage", "operating-systems");
        model.addAttribute("operatingSystems", operatingSystemService.getAllOperatingSystems());
        return "operating-systems/list";
    }

    @GetMapping("/maintenance-tickets")
    public String maintenanceTickets(Model model) {
        model.addAttribute("activePage", "maintenance-tickets");
        model.addAttribute("tickets", maintenanceTicketService.getAllMaintenanceTickets());
        return "maintenance-tickets/list";
    }
}
