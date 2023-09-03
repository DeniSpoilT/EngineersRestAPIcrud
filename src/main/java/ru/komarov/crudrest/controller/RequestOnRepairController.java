package ru.komarov.crudrest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.dto.RestResponse;
import ru.komarov.crudrest.service.RequestOnRepairService;

import static ru.komarov.crudrest.constant.Constant.CREATED;

@RestController
@Validated
@RequestMapping("/requests")
public class RequestOnRepairController {


    RequestOnRepairService requestOnRepairService;

    @Autowired
    public RequestOnRepairController(RequestOnRepairService requestOnRepairService) {
        this.requestOnRepairService = requestOnRepairService;
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RequestOnRepairDTO findById(@PathVariable Long id){
        return requestOnRepairService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse create(@RequestBody @Valid RequestOnRepairDTO requestOnRepairDTO) {
        requestOnRepairService.create(requestOnRepairDTO);
        return new RestResponse(CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse delete(@PathVariable Long id){
        requestOnRepairService.deleteById(id);
        return new RestResponse("id " + id + " deleted");
    }
}
