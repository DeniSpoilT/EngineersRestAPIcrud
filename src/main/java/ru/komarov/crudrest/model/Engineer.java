package ru.komarov.crudrest.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Engineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastName;

    private LocalDate birthdate;

    private boolean carAvailability;

    @JsonBackReference
    @OneToMany(mappedBy = "engineer")
    private List<RequestOnRepair> requests;
}
