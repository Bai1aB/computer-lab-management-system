package com.example.computerlab.controller.web;

import com.example.computerlab.dto.OperatingSystemForm;
import com.example.computerlab.model.OperatingSystem;
import com.example.computerlab.service.OperatingSystemService;
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
@RequestMapping("/operating-systems")
@RequiredArgsConstructor
public class OperatingSystemMvcController {

    private final OperatingSystemService operatingSystemService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("activePage", "operating-systems");
        model.addAttribute("operatingSystems", operatingSystemService.getAllOperatingSystems());
        return "operating-systems/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareForm(model, new OperatingSystemForm(), "Add Operating System");
        return "operating-systems/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("operatingSystemForm") OperatingSystemForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            prepareForm(model, form, "Add Operating System");
            return "operating-systems/form";
        }

        operatingSystemService.createOperatingSystem(toOperatingSystem(form));
        redirectAttributes.addFlashAttribute("successMessage", "Operating system created successfully.");
        return "redirect:/operating-systems";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        prepareForm(model, toForm(operatingSystemService.getOperatingSystemById(id)), "Edit Operating System");
        return "operating-systems/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("operatingSystemForm") OperatingSystemForm form,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            form.setId(id);
            prepareForm(model, form, "Edit Operating System");
            return "operating-systems/form";
        }

        operatingSystemService.updateOperatingSystem(id, toOperatingSystem(form));
        redirectAttributes.addFlashAttribute("successMessage", "Operating system updated successfully.");
        return "redirect:/operating-systems";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try {
            operatingSystemService.deleteOperatingSystem(id);
            redirectAttributes.addFlashAttribute("successMessage", "Operating system deleted successfully.");
        } catch (DataIntegrityViolationException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", "Operating system cannot be deleted while computers use it.");
        } catch (RuntimeException ex) {
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
        }
        return "redirect:/operating-systems";
    }

    private void prepareForm(Model model, OperatingSystemForm form, String pageTitle) {
        model.addAttribute("activePage", "operating-systems");
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("operatingSystemForm", form);
    }

    private OperatingSystem toOperatingSystem(OperatingSystemForm form) {
        OperatingSystem operatingSystem = new OperatingSystem();
        operatingSystem.setName(form.getName());
        operatingSystem.setVersion(form.getVersion());
        operatingSystem.setArchitecture(form.getArchitecture());
        return operatingSystem;
    }

    private OperatingSystemForm toForm(OperatingSystem operatingSystem) {
        OperatingSystemForm form = new OperatingSystemForm();
        form.setId(operatingSystem.getId());
        form.setName(operatingSystem.getName());
        form.setVersion(operatingSystem.getVersion());
        form.setArchitecture(operatingSystem.getArchitecture());
        return form;
    }
}
