package ru.komarov.crudrest.dto;

import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import ru.komarov.crudrest.dto.validation.MaxDate;
import ru.komarov.crudrest.dto.validation.MinDate;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Builder
@Getter
@Setter
public class EngineerDTO {

    private final static String NOT_EMPTY = "Should not be empty";
    private final static String LENGTH_OF_NAME_CONSTRAINT = "Should be contain from 2 to 15 characters";
    private final static String DATE_PATTERN = "yyyy-MM-dd";
    private final static String MAX_AGE_CONSTRAINT = "The engineer must be under 65 years of age";
    private final static String MIN_AGE_CONSTRAINT = "The engineer must be over 18 years old";
    private final static String CAR_AVAILABILITY_CONSTRAINT = "The presence of a car must be indicated";
    private final static int MAX_ENGINEER_AGE = 65*12;
    private final static int MIN_ENGINEER_AGE = 18*12;

    @NotBlank(message = NOT_EMPTY)
    @Size(min = 2, max = 15, message = LENGTH_OF_NAME_CONSTRAINT)
    private String name;

    @NotBlank(message = NOT_EMPTY)
    @Size(min = 2, max = 15, message = LENGTH_OF_NAME_CONSTRAINT)
    private String lastName;

    @DateTimeFormat(pattern = DATE_PATTERN)
    @MinDate(monthToReduce = MAX_ENGINEER_AGE, message = MAX_AGE_CONSTRAINT)
    @MaxDate(monthToReduce = MIN_ENGINEER_AGE, message = MIN_AGE_CONSTRAINT)
    private LocalDate birthdate;

    @NotBlank(message = CAR_AVAILABILITY_CONSTRAINT)
    private boolean carAvailability;

    @OneToMany(mappedBy = "EngineerId")
    private List<RequestOnRepair> requests;

}
