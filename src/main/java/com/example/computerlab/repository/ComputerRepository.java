package com.example.computerlab.repository;

import com.example.computerlab.enums.ComputerStatus;
import com.example.computerlab.model.Computer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComputerRepository extends JpaRepository<Computer, Long> {

    List<Computer> findByStatus(ComputerStatus status);

    List<Computer> findByLabRoomId(Long labRoomId);
}