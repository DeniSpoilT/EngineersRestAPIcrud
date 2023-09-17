package ru.komarov.crudrest.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.komarov.crudrest.dao.EngineerRepository;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.util.Optional;

@Component
@Qualifier("RequestDTOConverter")
public class RequestDTOConverter implements EntityDTOConverter<RequestOnRepairDTO, RequestOnRepair> {
    private final RequestOnRepairRepository repairRepository;
    private final EngineerRepository engineerRepository;

    @Autowired
    public RequestDTOConverter(RequestOnRepairRepository repairRepository, EngineerRepository engineerRepository) {
        this.repairRepository = repairRepository;
        this.engineerRepository = engineerRepository;
    }

    @Override
    public RequestOnRepairDTO toDTO(RequestOnRepair requestOnRepair) {

        RequestOnRepairDTO dto = toDTOWithoutRelatedEnteties(requestOnRepair);

        if (requestOnRepair.getEngineer() != null) {
            dto.setEngineerId(requestOnRepair.getEngineer().getId());
        }

        return dto;
    }

    @Override
    public RequestOnRepairDTO toDTOWithoutRelatedEnteties(RequestOnRepair requestOnRepair) {
        RequestOnRepairDTO dto = RequestOnRepairDTO.builder()
                .id(requestOnRepair.getId())
                .address(requestOnRepair.getAddress())
                .requestDate(requestOnRepair.getRequestDate())
                .contactPerson(requestOnRepair.getContactPerson())
                .phoneNumber(requestOnRepair.getPhoneNumber())
                .build();

        return dto;
    }

    @Override
    @Transactional
    public RequestOnRepair toEntity(RequestOnRepairDTO requestOnRepairDto) {

        RequestOnRepair entity = RequestOnRepair.builder()
                .id(requestOnRepairDto.getId())
                .address(requestOnRepairDto.getAddress())
                .requestDate(requestOnRepairDto.getRequestDate())
                .contactPerson(requestOnRepairDto.getContactPerson())
                .phoneNumber(requestOnRepairDto.getPhoneNumber())
                .build();

        if (requestOnRepairDto.getEngineerId() != null) {
            Optional<Engineer> optionalEngineer = engineerRepository.findById(requestOnRepairDto.getEngineerId());
            Engineer engineer = optionalEngineer.orElseThrow(() -> new NotFoundException("Engineer with id: "
                    + requestOnRepairDto.getEngineerId() + " not found."));
            entity.setEngineer(engineer);
            engineer.getRequests().add(entity);
        }

        return entity;
    }
}
