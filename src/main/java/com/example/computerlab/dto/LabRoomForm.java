package com.example.computerlab.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LabRoomForm {

    private Long id;

    @NotBlank(message = "Room number is required")
    private String roomNumber;

    @NotBlank(message = "Building is required")
    private String building;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be positive")
    private Integer capacity;
}
