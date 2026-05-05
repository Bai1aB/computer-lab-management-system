package com.example.computerlab.service;

import com.example.computerlab.enums.ComputerStatus;
import com.example.computerlab.model.Computer;
import com.example.computerlab.repository.ComputerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ComputerService {

    private final ComputerRepository computerRepository;

    public List<Computer> getAllComputers() {
        return computerRepository.findAll();
    }

    public Computer getComputerById(Long id) {
        return computerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Computer not found with id: " + id));
    }

    public Computer createComputer(Computer computer) {
        if (computer.getStatus() == null) {
            computer.setStatus(ComputerStatus.AVAILABLE);
        }

        return computerRepository.save(computer);
    }

    public Computer updateComputer(Long id, Computer updatedComputer) {
        Computer existingComputer = getComputerById(id);

        existingComputer.setName(updatedComputer.getName());
        existingComputer.setCpu(updatedComputer.getCpu());
        existingComputer.setRam(updatedComputer.getRam());
        existingComputer.setStorage(updatedComputer.getStorage());
        existingComputer.setStatus(updatedComputer.getStatus());
        existingComputer.setLabRoom(updatedComputer.getLabRoom());
        existingComputer.setOperatingSystem(updatedComputer.getOperatingSystem());

        return computerRepository.save(existingComputer);
    }

    public void deleteComputer(Long id) {
        Computer existingComputer = getComputerById(id);
        computerRepository.delete(existingComputer);
    }

    public List<Computer> getComputersByStatus(ComputerStatus status) {
        return computerRepository.findByStatus(status);
    }

    public List<Computer> getComputersByLabRoomId(Long labRoomId) {
        return computerRepository.findByLabRoomId(labRoomId);
    }
}