package ru.komarov.crudrest.dto.converter;

public interface EntityDTOConverter<DTO, Entity> {

    DTO toDTO(Entity entity);
    DTO toDTOWithoutRelatedEnteties(Entity entity);
    Entity toEntity(DTO dto);
}
