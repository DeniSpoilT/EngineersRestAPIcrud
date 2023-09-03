package ru.komarov.crudrest.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.util.List;


@Component
@Qualifier("EngineerDTOConverter")
public class EngineerDTOConverter implements EntityToDTOConverter<EngineerDTO, Engineer> {
    RequestOnRepairRepository requestOnRepairRepository;

    @Autowired
    public EngineerDTOConverter(RequestOnRepairRepository requestOnRepairRepository) {
        this.requestOnRepairRepository = requestOnRepairRepository;
    }

    @Override
    public EngineerDTO toDTO(Engineer engineer) {
        EngineerDTO engineerDTO = EngineerDTO.builder()
                .id(engineer.getId())
                .name(engineer.getName())
                .lastName(engineer.getLastName())
                .birthdate(engineer.getBirthdate())
                .carAvailability(engineer.isCarAvailability())
                .build();

        if (engineer.getRequests() != null) {
            engineerDTO.setRequests(engineer.getRequests());
        }
        return engineerDTO;
    }

    @Override
    public Engineer toEntity(EngineerDTO engineerDTO) {
        Engineer engineer = Engineer.builder()
                .id(engineerDTO.getId())
                .name(engineerDTO.getName())
                .lastName(engineerDTO.getLastName())
                .birthdate(engineerDTO.getBirthdate())
                .carAvailability(engineerDTO.getCarAvailability())
                .build();

        if (engineerDTO.getRequests() != null) {
            List<RequestOnRepair> requests = requestOnRepairRepository.findAllByEngineerId(engineerDTO.getId());
            engineer.setRequests(requests);
        }
        return engineer;
    }
}
