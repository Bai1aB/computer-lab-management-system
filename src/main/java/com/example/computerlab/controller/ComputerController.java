package com.example.computerlab.controller;

import com.example.computerlab.enums.ComputerStatus;
import com.example.computerlab.model.Computer;
import com.example.computerlab.service.ComputerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/computers")
@RequiredArgsConstructor
public class ComputerController {

    private final ComputerService computerService;

    @GetMapping
    public List<Computer> getAllComputers() {
        return computerService.getAllComputers();
    }

    @GetMapping("/{id}")
    public Computer getComputerById(@PathVariable Long id) {
        return computerService.getComputerById(id);
    }

    @GetMapping("/status/{status}")
    public List<Computer> getComputersByStatus(@PathVariable ComputerStatus status) {
        return computerService.getComputersByStatus(status);
    }

    @GetMapping("/labroom/{labRoomId}")
    public List<Computer> getComputersByLabRoomId(@PathVariable Long labRoomId) {
        return computerService.getComputersByLabRoomId(labRoomId);
    }

    @PostMapping
    public Computer createComputer(@RequestBody Computer computer) {
        return computerService.createComputer(computer);
    }

    @PutMapping("/{id}")
    public Computer updateComputer(@PathVariable Long id, @RequestBody Computer updatedComputer) {
        return computerService.updateComputer(id, updatedComputer);
    }

    @DeleteMapping("/{id}")
    public String deleteComputer(@PathVariable Long id) {
        computerService.deleteComputer(id);
        return "Computer deleted successfully";
    }
}