package com.example.computerlab.repository;

import com.example.computerlab.model.LabRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LabRoomRepository extends JpaRepository<LabRoom, Long> {
}