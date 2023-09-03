package ru.komarov.crudrest.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.dto.converter.RequestDTOConverter;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.model.RequestOnRepair;
import ru.komarov.crudrest.service.RequestOnRepairService;

import java.util.Optional;

import static ru.komarov.crudrest.constant.Constant.NOT_FOUND;

@RequiredArgsConstructor
@Service
public class RequestOnRepairServiceImpl implements RequestOnRepairService {

    private final RequestOnRepairRepository requestOnRepairRepository;
    private final RequestDTOConverter requestDTOConverter;

    @Override
    @Transactional
    public void create(RequestOnRepairDTO requestOnRepairDTO) {
        RequestOnRepair entity = requestDTOConverter.toEntity(requestOnRepairDTO);
        requestOnRepairRepository.save(entity);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        requestOnRepairRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(NOT_FOUND));
        requestOnRepairRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void update(RequestOnRepairDTO requestOnRepairDTO) {
        requestOnRepairRepository.findById(requestOnRepairDTO.getId())
                .orElseThrow(() -> new NotFoundException(NOT_FOUND));
        RequestOnRepair entity = requestDTOConverter.toEntity(requestOnRepairDTO);
        requestOnRepairRepository.save(entity);
    }

    @Override
    @Transactional
    public RequestOnRepairDTO findById(Long id) {
        Optional<RequestOnRepair> optionalRequestOnRepair = requestOnRepairRepository.findById(id);
        RequestOnRepair requestOnRepair = optionalRequestOnRepair.orElseThrow(()
                -> new NotFoundException("id: " + id + " not found"));
        return requestDTOConverter.toDTO(requestOnRepair);
    }


}