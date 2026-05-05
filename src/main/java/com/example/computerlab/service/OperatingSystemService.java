package com.example.computerlab.service;

import com.example.computerlab.model.OperatingSystem;
import com.example.computerlab.repository.OperatingSystemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatingSystemService {

    private final OperatingSystemRepository operatingSystemRepository;

    public List<OperatingSystem> getAllOperatingSystems() {
        return operatingSystemRepository.findAll();
    }

    public OperatingSystem getOperatingSystemById(Long id) {
        return operatingSystemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Operating system not found with id: " + id));
    }

    public OperatingSystem createOperatingSystem(OperatingSystem operatingSystem) {
        return operatingSystemRepository.save(operatingSystem);
    }

    public OperatingSystem updateOperatingSystem(Long id, OperatingSystem updatedOperatingSystem) {
        OperatingSystem existingOperatingSystem = getOperatingSystemById(id);

        existingOperatingSystem.setName(updatedOperatingSystem.getName());
        existingOperatingSystem.setVersion(updatedOperatingSystem.getVersion());
        existingOperatingSystem.setArchitecture(updatedOperatingSystem.getArchitecture());

        return operatingSystemRepository.save(existingOperatingSystem);
    }

    public void deleteOperatingSystem(Long id) {
        OperatingSystem existingOperatingSystem = getOperatingSystemById(id);
        operatingSystemRepository.delete(existingOperatingSystem);
    }
}