package ru.komarov.crudrest.service.impl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.dto.converter.EntityToDTOConverter;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.model.RequestOnRepair;
import ru.komarov.crudrest.service.RequestOnRepairService;

import java.util.List;
import java.util.Optional;

import static ru.komarov.crudrest.constant.Constant.REQUEST_ON_REPAIR_NOT_FOUND;

@Service
public class RequestOnRepairServiceImpl implements RequestOnRepairService {

    private final RequestOnRepairRepository requestOnRepairRepository;
    private final EntityToDTOConverter<RequestOnRepairDTO, RequestOnRepair> entityToDTOConverter;

    public RequestOnRepairServiceImpl(RequestOnRepairRepository requestOnRepairRepository,
                                      @Qualifier("RequestDTOConverter") EntityToDTOConverter entityToDTOConverter) {
        this.requestOnRepairRepository = requestOnRepairRepository;
        this.entityToDTOConverter = entityToDTOConverter;
    }

    @Override
    @Transactional
    public void create(RequestOnRepairDTO requestOnRepairDTO) {
        RequestOnRepair entity = entityToDTOConverter.toEntity(requestOnRepairDTO);
        requestOnRepairRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        requestOnRepairRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(REQUEST_ON_REPAIR_NOT_FOUND));
        requestOnRepairRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(@PathVariable Long id, @RequestBody RequestOnRepairDTO requestOnRepairDTO) {
        Optional<RequestOnRepair> optionalRequestOnRepair = requestOnRepairRepository.findById(id);
        RequestOnRepair requestOnRepair = optionalRequestOnRepair.orElseThrow(()
                -> new NotFoundException("id: " + id + " not found"));
        requestOnRepair.setAddress(requestOnRepairDTO.getAddress());
        requestOnRepair.setPhoneNumber(requestOnRepairDTO.getPhoneNumber());
        requestOnRepair.setRequestDate(requestOnRepairDTO.getRequestDate());
        requestOnRepair.setContactPerson(requestOnRepairDTO.getContactPerson());
        requestOnRepair.setEngineer(entityToDTOConverter.toEntity(requestOnRepairDTO).getEngineer());
    }

    @Override
    @Transactional
    public RequestOnRepairDTO findById(Long id) {
        Optional<RequestOnRepair> optionalRequestOnRepair = requestOnRepairRepository.findById(id);
        RequestOnRepair requestOnRepair = optionalRequestOnRepair.orElseThrow(()
                -> new NotFoundException("id: " + id + " not found"));
        return entityToDTOConverter.toDTO(requestOnRepair);
    }

    @Override
    public List<RequestOnRepair> findAll() {
        return requestOnRepairRepository.findAll();
    }

}