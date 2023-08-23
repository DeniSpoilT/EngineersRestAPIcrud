package ru.komarov.crudrest.service;

import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.model.Engineer;

public interface EngineerService {
    void create(EngineerDTO engineerDTO);
    void deleteById(Long id);
    void update(EngineerDTO engineerDTO);
    EngineerDTO findById(Long id);

}
