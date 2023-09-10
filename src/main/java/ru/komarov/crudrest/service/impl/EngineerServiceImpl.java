package ru.komarov.crudrest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.komarov.crudrest.dao.EngineersRepository;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.dto.converter.EntityDTOConverter;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.service.EngineerService;

import java.util.List;
import java.util.Optional;

import static ru.komarov.crudrest.constant.Constant.ENGINEER_NOT_FOUND;

@Service
public class EngineerServiceImpl implements EngineerService {

    private final EngineersRepository engineersRepository;
    private final EntityDTOConverter<EngineerDTO, Engineer> entityDtoConverter;

    @Autowired
    public EngineerServiceImpl(EngineersRepository engineersRepository,
                               @Qualifier("EngineerDTOConverter") EntityDTOConverter entityDtoConverter) {
        this.engineersRepository = engineersRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    @Transactional
    public void create(EngineerDTO engineerDto) {
        Engineer engineer = entityDtoConverter.toEntity(engineerDto);
        engineersRepository.save(engineer);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Engineer engineer = engineersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ENGINEER_NOT_FOUND));
        engineersRepository.delete(engineer);
    }

    @Override
    @Transactional
    public List<Engineer> findAll() {
        return engineersRepository.findAll();
    }


    @Override
    @Transactional
    public void update(Long id, EngineerDTO engineerDto) {
        Optional<Engineer> optionalEngineer = engineersRepository.findById(id);

        Engineer engineer = optionalEngineer.orElseThrow(()
                -> new NotFoundException("id: " + id + " not found"));

        engineer.setName(engineerDto.getName());
        engineer.setLastName(engineerDto.getLastName());
        engineer.setBirthdate(engineerDto.getBirthdate());
        engineer.setCarAvailability(engineerDto.getCarAvailability());
    }

    @Override
    @Transactional
    public EngineerDTO findById(Long id) {
        Optional<Engineer> optionalEngineer = engineersRepository.findById(id);

        Engineer engineer = optionalEngineer.orElseThrow(()
                -> new NotFoundException("id: " + id + " not found"));

        return entityDtoConverter.toDTO(engineer);
    }
}
