package ru.komarov.crudrest.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.time.LocalDate;

@Builder
@Getter
@Setter
public class RequestOnRepairDTO {

    private String address;
    private String contactPerson;
    private String phoneNumber;
    private LocalDate requestDate;
    private Long EngineerId;

}
