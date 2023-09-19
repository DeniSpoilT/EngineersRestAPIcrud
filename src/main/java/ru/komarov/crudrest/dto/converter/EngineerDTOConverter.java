package ru.komarov.crudrest.dto.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Qualifier("EngineerDTOConverter")
public class EngineerDTOConverter implements EntityDTOConverter<EngineerDTO, Engineer> {
    private final RequestOnRepairRepository requestOnRepairRepository;
    private final RequestDTOConverter requestDTOConverter;

    @Autowired
    public EngineerDTOConverter(RequestOnRepairRepository requestOnRepairRepository,
                                RequestDTOConverter requestDtoConverter) {
        this.requestOnRepairRepository = requestOnRepairRepository;
        this.requestDTOConverter = requestDtoConverter;
    }

    @Override
    public EngineerDTO toDTO(Engineer engineer) {
        EngineerDTO engineerDto = toDTOWithoutRelatedEnteties(engineer);

        if (engineer.getRequests() != null) {
            List<RequestOnRepair> requests = engineer.getRequests();
            List<RequestOnRepairDTO> requestDTOs = requests.stream()
                    .map(requestDTOConverter::toDTO)
                    .distinct()
                    .toList();
            engineerDto.setRequests(requestDTOs);
        }

        return engineerDto;
    }

    @Override
    public EngineerDTO toDTOWithoutRelatedEnteties(Engineer engineer) {
        EngineerDTO engineerDto = EngineerDTO.builder()
                .id(engineer.getId())
                .name(engineer.getName())
                .lastName(engineer.getLastName())
                .birthdate(engineer.getBirthdate())
                .carAvailability(engineer.isCarAvailability())
                .build();
        return engineerDto;
    }


    @Override
    public Engineer toEntity(EngineerDTO engineerDto) {
        Engineer engineer = Engineer.builder()
                .id(engineerDto.getId())
                .name(engineerDto.getName())
                .lastName(engineerDto.getLastName())
                .birthdate(engineerDto.getBirthdate())
                .carAvailability(engineerDto.getCarAvailability())
                .build();

        if (engineerDto.getRequests() != null) {
            List<RequestOnRepair> requests = requestOnRepairRepository.findAllByEngineerId(engineerDto.getId())
                    .stream()
                    .distinct()
                    .toList();
            engineer.setRequests(requests);
        }
        return engineer;
    }
}
