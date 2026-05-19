package com.example.computerlab.controller.web;

import com.example.computerlab.dto.LabRoomForm;
import com.example.computerlab.model.LabRoom;
import com.example.computerlab.service.LabRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/labrooms")
@RequiredArgsConstructor
public class LabRoomMvcController {

    private final LabRoomService labRoomService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("activePage", "labrooms");
        model.addAttribute("labRooms", labRoomService.getAllLabRooms());
        return "labrooms/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareForm(model, new LabRoomForm(), "Add Lab Room");
        return "labrooms/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("labRoomForm") LabRoomForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Add Lab Room");
            return "labrooms/form";
        }

        labRoomService.createLabRoom(toLabRoom(form));
        redirectAttributes.addFlashAttribute("successMessage", "Lab room created successfully.");
        return "redirect:/labrooms";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        prepareForm(model, toForm(labRoomService.getLabRoomById(id)), "Edit Lab Room");
        return "labrooms/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("labRoomForm") LabRoomForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            form.setId(id);
            prepareForm(model, form, "Edit Lab Room");
            return "labrooms/form";
        }

        labRoomService.updateLabRoom(id, toLabRoom(form));
        redirectAttributes.addFlashAttribute("successMessage", "Lab room updated successfully.");
        return "redirect:/labrooms";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            labRoomService.deleteLabRoom(id);
            redirectAttributes.addFlashAttribute("successMessage", "Lab room deleted successfully.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Lab room cannot be deleted while computers are assigned to it.");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/labrooms";
    }

    private void prepareForm(Model model, LabRoomForm form, String pageTitle) {
        model.addAttribute("activePage", "labrooms");
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("labRoomForm", form);
    }

    private LabRoom toLabRoom(LabRoomForm form) {
        LabRoom labRoom = new LabRoom();
        labRoom.setRoomNumber(form.getRoomNumber());
        labRoom.setBuilding(form.getBuilding());
        labRoom.setCapacity(form.getCapacity());
        return labRoom;
    }

    private LabRoomForm toForm(LabRoom labRoom) {
        LabRoomForm form = new LabRoomForm();
        form.setId(labRoom.getId());
        form.setRoomNumber(labRoom.getRoomNumber());
        form.setBuilding(labRoom.getBuilding());
        form.setCapacity(labRoom.getCapacity());
        return form;
    }
}
