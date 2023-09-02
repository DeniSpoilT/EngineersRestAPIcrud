package ru.komarov.crudrest.service;

import ru.komarov.crudrest.dto.RequestOnRepairDTO;

public interface RequestOnRepairService {
    void create(RequestOnRepairDTO requestOnRepairDTO);
    void deleteById(Long id);
    void update(RequestOnRepairDTO RequestOnRepairDTO);
    RequestOnRepairDTO findById(Long id);

}
