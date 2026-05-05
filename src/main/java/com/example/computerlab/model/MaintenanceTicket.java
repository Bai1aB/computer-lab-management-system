package com.example.computerlab.model;

import com.example.computerlab.enums.TicketPriority;
import com.example.computerlab.enums.TicketStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "maintenance_tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MaintenanceTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @Enumerated(EnumType.STRING)
    private TicketPriority priority;

    @Enumerated(EnumType.STRING)
    private TicketStatus status;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "computer_id")
    private Computer computer;
}