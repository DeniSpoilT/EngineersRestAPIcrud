package ru.komarov.crudrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @Operation(summary = "Get all Engineers")
    public List<Engineer> findAll() {
        return engineerService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add engineer with the specified parameters")
    public RestResponse create(@RequestBody @Valid EngineerDTO engineerDto) {
        engineerService.create(engineerDto);
        return new RestResponse(ENGINEER_CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete engineer with the specified id")
    public RestResponse delete(@PathVariable Long id) {
        engineerService.deleteById(id);
        return new RestResponse("id " + id + " deleted");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Modify engineer with the specified id")
    public RestResponse update(@PathVariable Long id, @RequestBody EngineerDTO engineerDto) {
        engineerService.update(id, engineerDto);
        return new RestResponse(ENGINEER_UPDATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get engineer with the specified id")
    public ResponseEntity<EngineerDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(engineerService.findById(id));
    }

}
