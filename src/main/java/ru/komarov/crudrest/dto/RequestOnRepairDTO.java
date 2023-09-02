package ru.komarov.crudrest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.komarov.crudrest.dto.validation.MinDate;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class RequestOnRepairDTO {

    private final static String ADDRESS_CONSTRAINT = "The address must be specified";
    private final static String CONTACT_CONSTRAINT = "The contact person must be specified";
    private final static String PHONE_CONSTRAINT = "The phone number must be specified";
    private final static String PHONE_PATTERN =  "\\d{3}-\\d{3}-\\d{4}";
    private final static String PHONE_PATTERN_CONSTRAINT =  "Incorrect number format";
    private final static String REQUEST_DATE_CONSTRAINT =  "The date of the repair request should not be earlier than a month ago";

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
