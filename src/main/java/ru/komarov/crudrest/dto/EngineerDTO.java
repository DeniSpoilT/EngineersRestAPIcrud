package ru.komarov.crudrest.dto;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.komarov.crudrest.dto.validation.MaxDate;
import ru.komarov.crudrest.dto.validation.MinDate;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.time.LocalDate;
import java.util.List;

import static ru.komarov.crudrest.constant.Constant.*;

@Builder
@Getter
@Setter
public class EngineerDTO {

    private Long id;

    @NotBlank(message = NAME_NOT_EMPTY)
    @Size(min = 2, max = 15, message = LENGTH_OF_NAME_CONSTRAINT)
    private String name;

    @NotBlank(message = LASTNAME_NOT_EMPTY)
    @Size(min = 2, max = 15, message = LENGTH_OF_NAME_CONSTRAINT)
    private String lastName;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @MinDate(monthToReduce = MAX_ENGINEER_AGE, message = MAX_AGE_CONSTRAINT)
    @MaxDate(monthToReduce = MIN_ENGINEER_AGE, message = MIN_AGE_CONSTRAINT)
    private LocalDate birthdate;

    @NotNull(message = CAR_AVAILABILITY_CONSTRAINT)
    private Boolean carAvailability;

    @OneToMany(mappedBy = "engineerId")
    private List<RequestOnRepair> requests;
}
