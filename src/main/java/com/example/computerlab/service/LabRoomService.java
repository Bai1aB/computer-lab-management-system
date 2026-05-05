package com.example.computerlab.service;

import com.example.computerlab.model.LabRoom;
import com.example.computerlab.repository.LabRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LabRoomService {

    private final LabRoomRepository labRoomRepository;

    public List<LabRoom> getAllLabRooms() {
        return labRoomRepository.findAll();
    }

    public LabRoom getLabRoomById(Long id) {
        return labRoomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lab room not found with id: " + id));
    }

    public LabRoom createLabRoom(LabRoom labRoom) {
        return labRoomRepository.save(labRoom);
    }

    public LabRoom updateLabRoom(Long id, LabRoom updatedLabRoom) {
        LabRoom existingLabRoom = getLabRoomById(id);

        existingLabRoom.setRoomNumber(updatedLabRoom.getRoomNumber());
        existingLabRoom.setBuilding(updatedLabRoom.getBuilding());
        existingLabRoom.setCapacity(updatedLabRoom.getCapacity());

        return labRoomRepository.save(existingLabRoom);
    }

    public void deleteLabRoom(Long id) {
        LabRoom existingLabRoom = getLabRoomById(id);
        labRoomRepository.delete(existingLabRoom);
    }
}