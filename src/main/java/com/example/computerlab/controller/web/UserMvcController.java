package com.example.computerlab.controller.web;

import com.example.computerlab.enums.UserRole;
import com.example.computerlab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserMvcController {

    private final UserService userService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("activePage", "users");
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("roles", UserRole.values());
        return "users/list";
    }

    @PostMapping("/{id}/role")
    public String updateRole(@PathVariable Long id,
                             @RequestParam UserRole role,
                             RedirectAttributes redirectAttributes) {
        userService.updateUserRole(id, role);
        redirectAttributes.addFlashAttribute("successMessage", "User role updated successfully.");
        return "redirect:/users";
    }
}
