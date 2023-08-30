package ru.komarov.crudrest.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.dto.converter.RequestDTOConverter;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.model.RequestOnRepair;
import ru.komarov.crudrest.service.RequestOnRepairService;

@RequiredArgsConstructor
@Service
public class RequestOnRepairServiceImpl implements RequestOnRepairService {

    private final RequestOnRepairRepository requestOnRepairRepository;
    private final RequestDTOConverter requestDTOConverter;

    @Override
    public void create(RequestOnRepairDTO requestOnRepairDTO) {
        RequestOnRepair entity = requestDTOConverter.toEntity(requestOnRepairDTO);
        requestOnRepairRepository.save(entity);
    }

    @Override
    public void deleteById(Long id) {
        requestOnRepairRepository.deleteById(id);
    }

    @Override
    public void update(RequestOnRepairDTO requestOnRepairDTO) {
        requestOnRepairRepository.findById(requestOnRepairDTO.getId())
                .orElseThrow(() -> new NotFoundException("Request On Repair not found"));
        RequestOnRepair entity = requestDTOConverter.toEntity(requestOnRepairDTO);
        requestOnRepairRepository.save(entity);
    }

    @Override
    public RequestOnRepairDTO findById(Long id) {
        return null;
    }


}