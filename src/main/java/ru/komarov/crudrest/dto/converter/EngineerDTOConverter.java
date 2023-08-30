package ru.komarov.crudrest.dto.converter;

import org.springframework.stereotype.Component;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.model.Engineer;
@Component
public class EngineerDTOConverter implements EntityToDTOConverter<EngineerDTO, Engineer>{
    @Override
    public EngineerDTO toDTO(Engineer engineer) {
        EngineerDTO dto = EngineerDTO.builder()

                .build();
        return dto;
    }

    @Override
    public Engineer toEntity(EngineerDTO engineerDTO) {
        Engineer entity = Engineer.builder()

                .build();
        return entity;
    }
}
