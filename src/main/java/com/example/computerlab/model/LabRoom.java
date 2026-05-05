package com.example.computerlab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lab_rooms")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LabRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomNumber;

    private String building;

    private Integer capacity;
}