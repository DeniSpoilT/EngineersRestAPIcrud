package ru.komarov.crudrest.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.komarov.crudrest.dao.EngineersRepository;
import ru.komarov.crudrest.dao.RequestOnRepairRepository;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.exception.NotFoundException;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.model.RequestOnRepair;
import ru.komarov.crudrest.service.RequestOnRepairService;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RequestOnRepairServiceImpl implements RequestOnRepairService {
    RequestOnRepairRepository requestOnRepairRepository;
    EngineersRepository engineersRepository;


    @Override
    public void create(RequestOnRepairDTO requestOnRepairDTO) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update(RequestOnRepairDTO RequestOnRepairDTO) {

    }

    @Override
    public RequestOnRepairDTO findById(Long id) {
        return null;
    }

    public RequestOnRepair engineerDTOToEngineer(RequestOnRepairDTO requestOnRepairDTO) {
        RequestOnRepair requestOnRepair = RequestOnRepair.builder()
                .address(requestOnRepairDTO.getAddress())
                .contactPerson(requestOnRepairDTO.getContactPerson())
                .phoneNumber(requestOnRepairDTO.getPhoneNumber())
                .requestDate(requestOnRepairDTO.getRequestDate())
                .build();

        if (requestOnRepairDTO.getEngineerId() != null) {
            Optional<Engineer> optionalRequestOnRepair =
                    engineersRepository.findById(requestOnRepairDTO.getEngineerId());
            optionalRequestOnRepair.ifPresentOrElse(requestOnRepair::setEngineerId,
                    () -> {
                        throw new NotFoundException("Request on repair " + requestOnRepairDTO.getEngineerId() + " not found");
                    });
        }
        return requestOnRepair;
    }
}