package ru.komarov.crudrest.service.impl;

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

    @Override
    public void create(EngineerDTO engineerDTO) {

    }

    @Override
    public void deleteById(Long id) {
    engineersRepository.deleteById(id);
    }

    @Override
    public void update(EngineerDTO engineerDTO) {

    }

    @Override
    public EngineerDTO findById(Long id) {
        return null;
    }
}
