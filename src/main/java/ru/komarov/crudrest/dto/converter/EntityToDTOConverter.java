package ru.komarov.crudrest.dto.converter;

public interface EntityToDTOConverter<DTO, Entity> {

    DTO toDTO(Entity entity);
    Entity toEntity(DTO dto);
}
