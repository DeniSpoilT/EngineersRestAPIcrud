package ru.komarov.crudrest.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class RequestOnRepairDTO {
    @NotBlank
    private Long id;
    @NotBlank
    private String address;
    @NotBlank
    private String contactPerson;
    @NotBlank
    private String phoneNumber;
    @NotBlank
    private LocalDate requestDate;
    private Long engineerId;

}
