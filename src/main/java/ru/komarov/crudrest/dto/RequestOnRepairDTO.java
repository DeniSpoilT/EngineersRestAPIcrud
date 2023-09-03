package ru.komarov.crudrest.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.komarov.crudrest.dto.validation.MinDate;

import java.time.LocalDate;

import static ru.komarov.crudrest.constant.Constant.*;

@Builder
@Getter
@Setter
public class RequestOnRepairDTO {

    private Long id;

    @NotBlank(message = ADDRESS_CONSTRAINT)
    @Size(min = 5, max = 100, message = ADDRESS_LENGTH_CONSTRAINT)
    private String address;

    @NotBlank(message = CONTACT_CONSTRAINT)
    @Size(min = 2, max = 30, message = CONTACT_LENGTH_CONSTRAINT)
    private String contactPerson;

    @NotBlank(message = PHONE_CONSTRAINT)
    @Pattern(regexp = PHONE_PATTERN, message = PHONE_PATTERN_CONSTRAINT)
    private String phoneNumber;

    @NotNull
    @MinDate(message = REQUEST_MIN_DATE_CONSTRAINT)
    @PastOrPresent(message = REQUEST_MAX_DATE_CONSTRAINT)
    private LocalDate requestDate;

    private Long engineerId;
}
