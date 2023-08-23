package ru.komarov.crudrest.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.komarov.crudrest.dao.EngineersRepository;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.service.EngineerService;
@RequiredArgsConstructor
@Service
public class EngineerServiceImpl implements EngineerService {
    private final EngineersRepository engineersRepository;
    RequestOnRepairRepository requestOnRepairRepository;

    @Override
    public void create(EngineerDTO engineerDTO) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(EngineerDTO engineerDTO) {

    }

    @Override
    public EngineerDTO findById(Long id) {
        return null;
    }
}
