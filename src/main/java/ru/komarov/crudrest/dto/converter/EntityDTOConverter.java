package ru.komarov.crudrest.dto.converter;

public interface EntityDTOConverter<DTO, Entity> {

    DTO toDTO(Entity entity);
    Entity toEntity(DTO dto);
}
