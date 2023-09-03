package ru.komarov.crudrest.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.komarov.crudrest.dao.EngineersRepository;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.service.EngineerService;
@RequiredArgsConstructor
@Service
public class EngineerServiceImpl implements EngineerService {

    private final EngineersRepository engineersRepository;

    @Override
    @Transactional
    public void create(EngineerDTO engineerDTO) {

    }

    @Override
    @Transactional
    public void deleteById(Long id) {
    engineersRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(EngineerDTO engineerDTO) {

    }

    @Override
    @Transactional
    public EngineerDTO findById(Long id) {
        return null;
    }
}
