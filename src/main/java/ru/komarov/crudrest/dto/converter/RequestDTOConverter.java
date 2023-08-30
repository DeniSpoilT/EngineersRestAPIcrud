package ru.komarov.crudrest.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.util.Optional;

@Component
public class RequestDTOConverter implements EntityToDTOConverter<RequestOnRepairDTO, RequestOnRepair> {
    RequestOnRepairRepository repairRepository;

    @Autowired
    public RequestDTOConverter(RequestOnRepairRepository repairRepository) {
        this.repairRepository = repairRepository;
    }

    @Override
    public RequestOnRepairDTO toDTO(RequestOnRepair requestOnRepair) {

        RequestOnRepairDTO dto = RequestOnRepairDTO.builder()
                .address(requestOnRepair.getAddress())
                .requestDate(requestOnRepair.getRequestDate())
                .contactPerson(requestOnRepair.getContactPerson())
                .phoneNumber(requestOnRepair.getPhoneNumber())
                .build();

        if (requestOnRepair.getEngineer() != null) {
            dto.setEngineerId(requestOnRepair.getEngineer().getEngineerId());
        }
        return dto;
    }

    @Override
    public RequestOnRepair toEntity(RequestOnRepairDTO requestOnRepairDTO) {

        RequestOnRepair entity = RequestOnRepair.builder()
                .address(requestOnRepairDTO.getAddress())
                .requestDate(requestOnRepairDTO.getRequestDate())
                .contactPerson(requestOnRepairDTO.getContactPerson())
                .phoneNumber(requestOnRepairDTO.getPhoneNumber())
                .build();

        if (requestOnRepairDTO.getEngineerId() != null) {
            Optional<RequestOnRepair> optionalRequestOnRepair = repairRepository.findById(requestOnRepairDTO.getEngineerId());
            Engineer engineer = optionalRequestOnRepair.orElseThrow(() -> new NotFoundException("Engineer with id: "
                            + requestOnRepairDTO.getEngineerId() + " not found."))
                    .getEngineer();
            entity.setEngineer(engineer);
        }
        return entity;
    }
}
