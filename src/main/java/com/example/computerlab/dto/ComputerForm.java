package com.example.computerlab.dto;

import com.example.computerlab.enums.ComputerStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComputerForm {

    private Long id;

    @NotBlank(message = "Computer name is required")
    private String name;

    @NotBlank(message = "CPU is required")
    private String cpu;

    @NotBlank(message = "RAM is required")
    private String ram;

    @NotBlank(message = "Storage is required")
    private String storage;

    @NotNull(message = "Status is required")
    private ComputerStatus status;

    @NotNull(message = "Lab room is required")
    private Long labRoomId;

    @NotNull(message = "Operating system is required")
    private Long operatingSystemId;
}
