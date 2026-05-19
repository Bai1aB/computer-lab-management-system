package com.example.computerlab.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperatingSystemForm {

    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Version is required")
    private String version;

    @NotBlank(message = "Architecture is required")
    private String architecture;
}
