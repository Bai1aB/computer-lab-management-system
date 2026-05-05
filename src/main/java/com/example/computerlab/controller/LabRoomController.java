package com.example.computerlab.controller;

import com.example.computerlab.model.LabRoom;
import com.example.computerlab.service.LabRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/labrooms")
@RequiredArgsConstructor
public class LabRoomController {

    private final LabRoomService labRoomService;

    @GetMapping
    public List<LabRoom> getAllLabRooms() {
        return labRoomService.getAllLabRooms();
    }

    @GetMapping("/{id}")
    public LabRoom getLabRoomById(@PathVariable Long id) {
        return labRoomService.getLabRoomById(id);
    }

    @PostMapping
    public LabRoom createLabRoom(@RequestBody LabRoom labRoom) {
        return labRoomService.createLabRoom(labRoom);
    }

    @PutMapping("/{id}")
    public LabRoom updateLabRoom(@PathVariable Long id, @RequestBody LabRoom updatedLabRoom) {
        return labRoomService.updateLabRoom(id, updatedLabRoom);
    }

    @DeleteMapping("/{id}")
    public String deleteLabRoom(@PathVariable Long id) {
        labRoomService.deleteLabRoom(id);
        return "Lab room deleted successfully";
    }
}