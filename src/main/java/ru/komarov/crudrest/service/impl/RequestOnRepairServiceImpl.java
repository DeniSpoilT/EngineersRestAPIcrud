package ru.komarov.crudrest.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.dto.converter.EntityDTOConverter;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.model.RequestOnRepair;
import ru.komarov.crudrest.service.RequestOnRepairService;

import java.util.List;
import java.util.Optional;

import static ru.komarov.crudrest.constant.Constant.REQUEST_ON_REPAIR_NOT_FOUND;

@Service
public class RequestOnRepairServiceImpl implements RequestOnRepairService {

    private final RequestOnRepairRepository requestOnRepairRepository;
    private final EntityDTOConverter<RequestOnRepairDTO, RequestOnRepair> entityDtoConverter;

    public RequestOnRepairServiceImpl(RequestOnRepairRepository requestOnRepairRepository,
                                      @Qualifier("RequestDTOConverter") EntityDTOConverter entityDtoConverter) {
        this.requestOnRepairRepository = requestOnRepairRepository;
        this.entityDtoConverter = entityDtoConverter;
    }

    @Override
    @Transactional
    public void create(RequestOnRepairDTO requestOnRepairDto) {
        RequestOnRepair requestOnRepair = entityDtoConverter.toEntity(requestOnRepairDto);
        requestOnRepairRepository.save(requestOnRepair);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        RequestOnRepair requestOnRepair = requestOnRepairRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(REQUEST_ON_REPAIR_NOT_FOUND));
        requestOnRepairRepository.delete(requestOnRepair);
    }

    @Override
    @Transactional
    public void update(Long id, RequestOnRepairDTO requestOnRepairDto) {
        Optional<RequestOnRepair> optionalRequestOnRepair = requestOnRepairRepository.findById(id);

        RequestOnRepair requestOnRepair = optionalRequestOnRepair.orElseThrow(()
                -> new NotFoundException("id: " + id + " not found"));

        requestOnRepair.setAddress(requestOnRepairDto.getAddress());
        requestOnRepair.setPhoneNumber(requestOnRepairDto.getPhoneNumber());
        requestOnRepair.setRequestDate(requestOnRepairDto.getRequestDate());
        requestOnRepair.setContactPerson(requestOnRepairDto.getContactPerson());
        requestOnRepair.setEngineer(entityDtoConverter.toEntity(requestOnRepairDto).getEngineer());
    }

    @Override
    @Transactional
    public RequestOnRepairDTO findById(Long id) {
        Optional<RequestOnRepair> optionalRequestOnRepair = requestOnRepairRepository.findById(id);

        RequestOnRepair requestOnRepair = optionalRequestOnRepair.orElseThrow(()
                -> new NotFoundException("id: " + id + " not found"));

        return entityDtoConverter.toDTO(requestOnRepair);
    }

    @Override
    @Transactional
    public List<RequestOnRepairDTO> findAll() {
        List<RequestOnRepairDTO> requests = requestOnRepairRepository.findAll()
                .stream()
                .map(requestOnRepair -> entityDtoConverter.toDTO(requestOnRepair))
                .toList();
        return requests;
    }
}