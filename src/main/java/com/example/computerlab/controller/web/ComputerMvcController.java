package com.example.computerlab.controller.web;

import com.example.computerlab.dto.ComputerForm;
import com.example.computerlab.enums.ComputerStatus;
import com.example.computerlab.model.Computer;
import com.example.computerlab.service.ComputerService;
import com.example.computerlab.service.LabRoomService;
import com.example.computerlab.service.OperatingSystemService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/computers")
@RequiredArgsConstructor
public class ComputerMvcController {

    private final ComputerService computerService;
    private final LabRoomService labRoomService;
    private final OperatingSystemService operatingSystemService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("activePage", "computers");
        model.addAttribute("computers", computerService.getAllComputers());
        return "computers/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        ComputerForm form = new ComputerForm();
        form.setStatus(ComputerStatus.AVAILABLE);
        prepareForm(model, form, "Add Computer");
        return "computers/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("computerForm") ComputerForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Add Computer");
            return "computers/form";
        }

        computerService.createComputer(toComputer(form));
        redirectAttributes.addFlashAttribute("successMessage", "Computer created successfully.");
        return "redirect:/computers";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        prepareForm(model, toForm(computerService.getComputerById(id)), "Edit Computer");
        return "computers/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("computerForm") ComputerForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            form.setId(id);
            prepareForm(model, form, "Edit Computer");
            return "computers/form";
        }

        computerService.updateComputer(id, toComputer(form));
        redirectAttributes.addFlashAttribute("successMessage", "Computer updated successfully.");
        return "redirect:/computers";
    }

    private void prepareForm(Model model, ComputerForm form, String pageTitle) {
        model.addAttribute("activePage", "computers");
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("computerForm", form);
        model.addAttribute("statuses", ComputerStatus.values());
        model.addAttribute("labRooms", labRoomService.getAllLabRooms());
        model.addAttribute("operatingSystems", operatingSystemService.getAllOperatingSystems());
    }

    private Computer toComputer(ComputerForm form) {
        Computer computer = new Computer();
        computer.setName(form.getName());
        computer.setCpu(form.getCpu());
        computer.setRam(form.getRam());
        computer.setStorage(form.getStorage());
        computer.setStatus(form.getStatus());
        computer.setLabRoom(labRoomService.getLabRoomById(form.getLabRoomId()));
        computer.setOperatingSystem(operatingSystemService.getOperatingSystemById(form.getOperatingSystemId()));
        return computer;
    }

    private ComputerForm toForm(Computer computer) {
        ComputerForm form = new ComputerForm();
        form.setId(computer.getId());
        form.setName(computer.getName());
        form.setCpu(computer.getCpu());
        form.setRam(computer.getRam());
        form.setStorage(computer.getStorage());
        form.setStatus(computer.getStatus());
        if (computer.getLabRoom() != null) {
            form.setLabRoomId(computer.getLabRoom().getId());
        }
        if (computer.getOperatingSystem() != null) {
            form.setOperatingSystemId(computer.getOperatingSystem().getId());
        }
        return form;
    }
}
