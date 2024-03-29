package ru.komarov.crudrest.service;

import ru.komarov.crudrest.dto.EngineerDTO;

import java.util.List;

public interface EngineerService {

    void create(EngineerDTO engineerDTO);

    void deleteById(Long id);

    void update(Long id, EngineerDTO engineerDTO);

    EngineerDTO findById(Long id);

    List<EngineerDTO> findAll();

    List<EngineerDTO> findAllEngineersWithRequests();
}
