package ru.komarov.crudrest.service.impl;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.komarov.crudrest.dao.EngineersRepository;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.dto.converter.EntityToDTOConverter;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.service.EngineerService;

import java.util.List;
import java.util.Optional;

import static ru.komarov.crudrest.constant.Constant.ENGINEER_NOT_FOUND;

@Service
public class EngineerServiceImpl implements EngineerService {

    private final EngineersRepository engineersRepository;
    private final EntityToDTOConverter<EngineerDTO, Engineer> entityToDTOConverter;

    @Autowired
    public EngineerServiceImpl(EngineersRepository engineersRepository,
                               @Qualifier("EngineerDTOConverter") EntityToDTOConverter entityToDTOConverter) {
        this.engineersRepository = engineersRepository;
        this.entityToDTOConverter = entityToDTOConverter;
    }

    @Override
    @Transactional
    public void create(EngineerDTO engineerDTO) {
        Engineer entity = entityToDTOConverter.toEntity(engineerDTO);
        engineersRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        engineersRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(ENGINEER_NOT_FOUND));
        engineersRepository.deleteById(id);
    }

    @Override
    @Transactional
    public List<Engineer> findAll() {
        return engineersRepository.findAll();
    }


    @Override
    @Transactional
    public void update(@PathVariable Long id, @RequestBody @Valid EngineerDTO engineerDTO) {
        Optional<Engineer> optionalEngineer = engineersRepository.findById(id);
        Engineer engineer = optionalEngineer.orElseThrow(()
                -> new NotFoundException("id: " + id + " not found"));
        engineer.setName(engineerDTO.getName());
        engineer.setLastName(engineerDTO.getLastName());
        engineer.setBirthdate(engineerDTO.getBirthdate());
        engineer.setCarAvailability(engineerDTO.getCarAvailability());
    }

    @Override
    @Transactional
    public EngineerDTO findById(Long id) {
        Optional<Engineer> optionalEngineer = engineersRepository.findById(id);
        Engineer engineer = optionalEngineer.orElseThrow(()
                -> new NotFoundException("id: " + id + " not found"));
        return entityToDTOConverter.toDTO(engineer);
    }
}
