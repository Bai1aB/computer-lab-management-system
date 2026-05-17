package com.example.computerlab.controller.web;

import com.example.computerlab.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserMvcController {

    private final UserService userService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("activePage", "users");
        model.addAttribute("users", userService.getAllUsers());
        return "users/list";
    }
}
