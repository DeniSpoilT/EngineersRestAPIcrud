package ru.komarov.crudrest.service;

import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.model.RequestOnRepair;

public interface RequestOnRepairService {
    void create(RequestOnRepairDTO requestOnRepairDTO);
    void deleteById(Long id);
    void update(RequestOnRepairDTO RequestOnRepairDTO);
    RequestOnRepairDTO findById(Long id);

}
