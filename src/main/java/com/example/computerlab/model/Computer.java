package com.example.computerlab.model;

import com.example.computerlab.enums.ComputerStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "computers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Computer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String cpu;

    private String ram;

    private String storage;

    @Enumerated(EnumType.STRING)
    private ComputerStatus status;

    @ManyToOne
    @JoinColumn(name = "lab_room_id")
    private LabRoom labRoom;

    @ManyToOne
    @JoinColumn(name = "operating_system_id")
    private OperatingSystem operatingSystem;
}