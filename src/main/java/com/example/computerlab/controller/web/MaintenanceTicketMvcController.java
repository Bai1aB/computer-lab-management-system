package com.example.computerlab.controller.web;

import com.example.computerlab.dto.MaintenanceTicketForm;
import com.example.computerlab.enums.TicketPriority;
import com.example.computerlab.enums.TicketStatus;
import com.example.computerlab.model.Computer;
import com.example.computerlab.model.MaintenanceTicket;
import com.example.computerlab.service.ComputerService;
import com.example.computerlab.service.MaintenanceTicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/maintenance-tickets")
@RequiredArgsConstructor
public class MaintenanceTicketMvcController {

    private final MaintenanceTicketService maintenanceTicketService;
    private final ComputerService computerService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("activePage", "maintenance-tickets");
        model.addAttribute("tickets", maintenanceTicketService.getAllMaintenanceTickets());
        model.addAttribute("statuses", TicketStatus.values());
        return "maintenance-tickets/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareForm(model, new MaintenanceTicketForm());
        return "maintenance-tickets/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("maintenanceTicketForm") MaintenanceTicketForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form);
            return "maintenance-tickets/form";
        }

        try {
            MaintenanceTicket ticket = new MaintenanceTicket();
            Computer computer = new Computer();
            computer.setId(form.getComputerId());
            ticket.setComputer(computer);
            ticket.setDescription(form.getDescription());
            ticket.setPriority(form.getPriority());
            maintenanceTicketService.createMaintenanceTicket(ticket);
            redirectAttributes.addFlashAttribute("successMessage", "Maintenance ticket created successfully.");
            return "redirect:/maintenance-tickets";
        } catch (RuntimeException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            prepareForm(model, form);
            return "maintenance-tickets/form";
        }
    }

    @PostMapping("/{id}/status")
    public String updateStatus(@PathVariable Long id,
                               @RequestParam TicketStatus status,
                               RedirectAttributes redirectAttributes) {
        maintenanceTicketService.updateTicketStatus(id, status);
        redirectAttributes.addFlashAttribute("successMessage", "Ticket status updated successfully.");
        return "redirect:/maintenance-tickets";
    }

    private void prepareForm(Model model, MaintenanceTicketForm form) {
        model.addAttribute("activePage", "maintenance-tickets");
        model.addAttribute("maintenanceTicketForm", form);
        model.addAttribute("computers", computerService.getAllComputers());
        model.addAttribute("priorities", TicketPriority.values());
    }
}
