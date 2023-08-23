package ru.komarov.crudrest.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Engineer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;
    private LocalDate birthdate;
    private boolean carAvailability;
    @OneToMany(mappedBy = "EngineerId")
    private List<RequestOnRepair> requests;

}
