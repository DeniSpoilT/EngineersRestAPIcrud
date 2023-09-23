package ru.komarov.crudrest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.komarov.crudrest.dao.EngineerRepository;
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

    private final EngineerRepository engineerRepository;
    private final EntityDTOConverter<EngineerDTO, Engineer> entityDtoConverter;

    @Autowired
    public EngineerServiceImpl(EngineerRepository engineerRepository,
                               @Qualifier("EngineerDTOConverter") EntityDTOConverter entityDtoConverter) {
        this.engineerRepository = engineerRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    @Transactional
    public void create(EngineerDTO engineerDto) {
        Engineer engineer = entityDtoConverter.toEntity(engineerDto);
        engineerRepository.save(engineer);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Engineer engineer = engineerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(id,  ENGINEER_NOT_FOUND));
        engineerRepository.delete(engineer);
    }

    @Override
    @Transactional
    public List<EngineerDTO> findAll() {
        List<EngineerDTO> engineersDto = engineerRepository.findAll()
                .stream()
                .map(engineer -> entityDtoConverter.toDTOWithoutRelatedEnteties(engineer))
                .toList();
        return engineersDto;
    }

    @Override
    @Transactional
    public List<EngineerDTO> findAllEngineersWithRequests() {
        List<Engineer> engineers = engineerRepository.findAllEngineersWithRequests();
        List<EngineerDTO>  engineersDto = engineers.stream()
                .map(engineer -> entityDtoConverter.toDTO(engineer))
                .toList();
        return engineersDto;
    }


    @Override
    @Transactional
    public void update(Long id, EngineerDTO engineerDto) {
        Optional<Engineer> optionalEngineer = engineerRepository.findById(id);

        Engineer engineer = optionalEngineer.orElseThrow(()
                -> new NotFoundException(id,  ENGINEER_NOT_FOUND));

        engineer.setName(engineerDto.getName());
        engineer.setLastName(engineerDto.getLastName());
        engineer.setBirthdate(engineerDto.getBirthdate());
        engineer.setCarAvailability(engineerDto.getCarAvailability());
    }

    @Override
    @Transactional
    public EngineerDTO findById(Long id) {
        Optional<Engineer> optionalEngineer = engineerRepository.findById(id);

        Engineer engineer = optionalEngineer.orElseThrow(()
                -> new NotFoundException(id,  ENGINEER_NOT_FOUND));

        return entityDtoConverter.toDTO(engineer);
    }
}
