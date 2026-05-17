package com.example.computerlab.controller.web;

import com.example.computerlab.enums.ComputerStatus;
import com.example.computerlab.enums.TicketStatus;
import com.example.computerlab.service.BookingService;
import com.example.computerlab.service.ComputerService;
import com.example.computerlab.service.LabRoomService;
import com.example.computerlab.service.MaintenanceTicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardMvcController {

    private final ComputerService computerService;
    private final LabRoomService labRoomService;
    private final BookingService bookingService;
    private final MaintenanceTicketService maintenanceTicketService;

    @GetMapping({"/", "/dashboard"})
    public String dashboard(Model model) {
        var computers = computerService.getAllComputers();
        var tickets = maintenanceTicketService.getAllMaintenanceTickets();

        model.addAttribute("activePage", "dashboard");
        model.addAttribute("totalComputers", computers.size());
        model.addAttribute("availableComputers", computers.stream()
                .filter(computer -> computer.getStatus() == ComputerStatus.AVAILABLE)
                .count());
        model.addAttribute("labRoomCount", labRoomService.getAllLabRooms().size());
        model.addAttribute("activeBookings", bookingService.getAllBookings().size());
        model.addAttribute("openTickets", tickets.stream()
                .filter(ticket -> ticket.getStatus() == TicketStatus.OPEN || ticket.getStatus() == TicketStatus.IN_PROGRESS)
                .count());
        model.addAttribute("recentTickets", tickets.stream().limit(5).toList());
        model.addAttribute("recentComputers", computers.stream().limit(5).toList());
        return "dashboard";
    }
}
