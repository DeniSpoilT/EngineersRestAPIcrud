package ru.komarov.crudrest.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.komarov.crudrest.dao.EngineersRepository;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.util.Optional;

@Component
@Qualifier("RequestDTOConverter")
public class RequestDTOConverter implements EntityToDTOConverter<RequestOnRepairDTO, RequestOnRepair> {
    RequestOnRepairRepository repairRepository;
    EngineersRepository engineersRepository;

    @Autowired
    public RequestDTOConverter(RequestOnRepairRepository repairRepository, EngineersRepository engineersRepository) {
        this.repairRepository = repairRepository;
        this.engineersRepository = engineersRepository;
    }

    @Override
    public RequestOnRepairDTO toDTO(RequestOnRepair requestOnRepair) {

        RequestOnRepairDTO dto = RequestOnRepairDTO.builder()
                .id(requestOnRepair.getId())
                .address(requestOnRepair.getAddress())
                .requestDate(requestOnRepair.getRequestDate())
                .contactPerson(requestOnRepair.getContactPerson())
                .phoneNumber(requestOnRepair.getPhoneNumber())
                .build();

        if (requestOnRepair.getEngineer() != null) {
            dto.setEngineerId(requestOnRepair.getEngineer().getId());
        }
        return dto;
    }

    @Override
    @Transactional
    public RequestOnRepair toEntity(RequestOnRepairDTO requestOnRepairDTO) {

        RequestOnRepair entity = RequestOnRepair.builder()
                .id(requestOnRepairDTO.getId())
                .address(requestOnRepairDTO.getAddress())
                .requestDate(requestOnRepairDTO.getRequestDate())
                .contactPerson(requestOnRepairDTO.getContactPerson())
                .phoneNumber(requestOnRepairDTO.getPhoneNumber())
                .build();

        if (requestOnRepairDTO.getEngineerId() != null) {
            Optional<Engineer> optionalEngineer = engineersRepository.findById(requestOnRepairDTO.getEngineerId());
            Engineer engineer = optionalEngineer.orElseThrow(() -> new NotFoundException("Engineer with id: "
                    + requestOnRepairDTO.getEngineerId() + " not found."));
            entity.setEngineer(engineer);
        }
        return entity;
    }
}
