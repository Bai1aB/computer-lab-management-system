package com.example.computerlab.controller;

import com.example.computerlab.model.OperatingSystem;
import com.example.computerlab.service.OperatingSystemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/operating-systems")
@RequiredArgsConstructor
public class OperatingSystemController {

    private final OperatingSystemService operatingSystemService;

    @GetMapping
    public List<OperatingSystem> getAllOperatingSystems() {
        return operatingSystemService.getAllOperatingSystems();
    }

    @GetMapping("/{id}")
    public OperatingSystem getOperatingSystemById(@PathVariable Long id) {
        return operatingSystemService.getOperatingSystemById(id);
    }

    @PostMapping
    public OperatingSystem createOperatingSystem(@RequestBody OperatingSystem operatingSystem) {
        return operatingSystemService.createOperatingSystem(operatingSystem);
    }

    @PutMapping("/{id}")
    public OperatingSystem updateOperatingSystem(@PathVariable Long id,
                                                 @RequestBody OperatingSystem updatedOperatingSystem) {
        return operatingSystemService.updateOperatingSystem(id, updatedOperatingSystem);
    }

    @DeleteMapping("/{id}")
    public String deleteOperatingSystem(@PathVariable Long id) {
        operatingSystemService.deleteOperatingSystem(id);
        return "Operating system deleted successfully";
    }
}