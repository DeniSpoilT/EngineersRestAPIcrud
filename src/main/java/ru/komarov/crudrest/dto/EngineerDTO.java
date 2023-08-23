package ru.komarov.crudrest.dto;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.time.LocalDate;
import java.util.List;
@Builder
@Getter@Setter
public class EngineerDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;
    private LocalDate birthdate;
    private boolean carAvailability;
    @OneToMany(mappedBy = "EngineerId")
    private List<RequestOnRepair> requests;

    public static Engineer engineerDTOToEngineer(EngineerDTO engineerDTO){
        Engineer engineer = Engineer.builder()
                .name(engineerDTO.name)
                .lastName(engineerDTO.lastName)
                .birthdate(engineerDTO.birthdate)
                .carAvailability(engineerDTO.carAvailability)
                .build();
        return engineer;
    }
}
