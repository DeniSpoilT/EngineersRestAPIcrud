package ru.komarov.crudrest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestOnRepair {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String address;

    private String contactPerson;

    private String phoneNumber;

    private LocalDate requestDate;

    @ManyToOne
    @JoinColumn(name = "engineer_id")
    private Engineer engineer;
}
