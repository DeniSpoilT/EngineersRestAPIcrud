package ru.komarov.crudrest.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "engineer_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Engineer engineer;
}
