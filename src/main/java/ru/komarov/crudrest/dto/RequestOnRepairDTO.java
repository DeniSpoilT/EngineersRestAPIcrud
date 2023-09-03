package ru.komarov.crudrest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
    private String address;

    @NotBlank(message = CONTACT_CONSTRAINT)
    private String contactPerson;

    @NotBlank(message = CONTACT_CONSTRAINT)
    @Pattern(regexp = PHONE_PATTERN, message = PHONE_PATTERN_CONSTRAINT)
    private String phoneNumber;

    @NotNull
    @MinDate(message = REQUEST_DATE_CONSTRAINT)
    private LocalDate requestDate;

    private Long engineerId;

}
