package ru.komarov.crudrest.service;

import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.model.RequestOnRepair;

import java.util.List;

public interface RequestOnRepairService {

    void create(RequestOnRepairDTO requestOnRepairDTO);

    void deleteById(Long id);

    void update(Long id, RequestOnRepairDTO requestOnRepairDTO);

    RequestOnRepairDTO findById(Long id);

    List<RequestOnRepair> findAll();
}
