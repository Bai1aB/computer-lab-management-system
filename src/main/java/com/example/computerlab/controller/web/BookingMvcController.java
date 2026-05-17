package com.example.computerlab.controller.web;

import com.example.computerlab.dto.BookingForm;
import com.example.computerlab.model.Booking;
import com.example.computerlab.model.Computer;
import com.example.computerlab.model.User;
import com.example.computerlab.service.BookingService;
import com.example.computerlab.service.ComputerService;
import com.example.computerlab.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class BookingMvcController {

    private final BookingService bookingService;
    private final ComputerService computerService;
    private final UserService userService;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("activePage", "bookings");
        model.addAttribute("bookings", bookingService.getAllBookings());
        return "bookings/list";
    }

    @GetMapping("/new")
    public String createForm(Model model) {
        prepareForm(model, new BookingForm());
        return "bookings/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("bookingForm") BookingForm form,
                         BindingResult bindingResult,
                         Authentication authentication,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (form.getStartTime() != null && form.getEndTime() != null && !form.getEndTime().isAfter(form.getStartTime())) {
            bindingResult.rejectValue("endTime", "endTime.invalid", "End time must be after start time");
        }

        if (bindingResult.hasErrors()) {
            prepareForm(model, form);
            return "bookings/form";
        }

        User user = userService.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));
        Computer computer = new Computer();
        computer.setId(form.getComputerId());

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setComputer(computer);
        booking.setStartTime(form.getStartTime());
        booking.setEndTime(form.getEndTime());
        booking.setPurpose(form.getPurpose());

        bookingService.createBooking(booking);
        redirectAttributes.addFlashAttribute("successMessage", "Booking created successfully.");
        return "redirect:/bookings";
    }

    private void prepareForm(Model model, BookingForm form) {
        model.addAttribute("activePage", "bookings");
        model.addAttribute("bookingForm", form);
        model.addAttribute("computers", computerService.getAllComputers());
    }
}
