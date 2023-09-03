package ru.komarov.crudrest.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.komarov.crudrest.dto.EngineerDTO;
import ru.komarov.crudrest.dto.RestResponse;
import ru.komarov.crudrest.model.Engineer;
import ru.komarov.crudrest.service.EngineerService;


import java.util.List;

import static ru.komarov.crudrest.constant.Constant.ENGINEER_CREATED;
import static ru.komarov.crudrest.constant.Constant.ENGINEER_UPDATED;

@RestController
@Validated
@RequestMapping("/engineers")
public class EngineerController {

    EngineerService engineerService;

    @Autowired
    public EngineerController(EngineerService engineerService) {
        this.engineerService = engineerService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Engineer> findAll(){
        return engineerService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public EngineerDTO findById(@PathVariable Long id) {
        return engineerService.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse delete(@PathVariable Long id) {
        engineerService.deleteById(id);
        return new RestResponse("id " + id + " deleted");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public RestResponse update(@PathVariable Long id, @RequestBody EngineerDTO engineerDTO){
        engineerService.update(id, engineerDTO);
        return new RestResponse(ENGINEER_UPDATED);
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RestResponse create(@RequestBody @Valid EngineerDTO engineerDTO){
        engineerService.create(engineerDTO);
        return new RestResponse(ENGINEER_CREATED);
    }



}
