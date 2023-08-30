package ru.komarov.crudrest.dto;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
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
    @NotBlank
    private boolean carAvailability;
    @OneToMany(mappedBy = "EngineerId")
    private List<RequestOnRepair> requests;

}
