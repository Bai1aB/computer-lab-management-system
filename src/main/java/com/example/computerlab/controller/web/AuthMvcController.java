package com.example.computerlab.controller.web;

import com.example.computerlab.dto.RegistrationForm;
import com.example.computerlab.enums.UserRole;
import com.example.computerlab.model.User;
import com.example.computerlab.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
public class AuthMvcController {

    private final UserService userService;

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "auth/register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("registrationForm") RegistrationForm form,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {
        if (userService.emailExists(form.getEmail())) {
            bindingResult.rejectValue("email", "email.exists", "An account with this email already exists");
        }

        if (bindingResult.hasErrors()) {
            return "auth/register";
        }

        User user = new User();
        user.setFullName(form.getFullName());
        user.setEmail(form.getEmail());
        user.setPassword(form.getPassword());
        user.setRole(UserRole.STUDENT);
        userService.createUser(user);

        redirectAttributes.addFlashAttribute("successMessage", "Registration complete. Please sign in.");
        return "redirect:/login";
    }
}
