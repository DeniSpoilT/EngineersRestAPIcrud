package ru.komarov.crudrest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.dto.RestResponse;
import ru.komarov.crudrest.service.RequestOnRepairService;

@RestController
@RequestMapping("/requests")
@RequiredArgsConstructor
public class RequestOnRepairController {

    RequestOnRepairService requestOnRepairService;

    @Autowired
    public RequestOnRepairController(RequestOnRepairService requestOnRepairService) {
        this.requestOnRepairService = requestOnRepairService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse create(@RequestBody RequestOnRepairDTO requestOnRepairDTO) {
        requestOnRepairService.create(requestOnRepairDTO);
        return new RestResponse("Request on repair created");
    }
}
