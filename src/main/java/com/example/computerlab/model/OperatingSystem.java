package com.example.computerlab.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "operating_systems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OperatingSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String version;

    private String architecture;
}