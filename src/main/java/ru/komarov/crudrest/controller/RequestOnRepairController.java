package ru.komarov.crudrest.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.komarov.crudrest.dto.RequestOnRepairDTO;
import ru.komarov.crudrest.dto.RestResponse;
import ru.komarov.crudrest.model.RequestOnRepair;
import ru.komarov.crudrest.service.RequestOnRepairService;

import java.util.List;

import static ru.komarov.crudrest.constant.Constant.*;

@RestController
@Validated
@RequestMapping("/requests")
@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ok",
        content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = RequestOnRepair.class))}),
        @ApiResponse(responseCode = "201", description = REQUEST_ON_REPAIR_CREATED),
        @ApiResponse(responseCode = "400", description = INVALID_OPERATION),
        @ApiResponse(responseCode = "404", description = REQUEST_ON_REPAIR_NOT_FOUND)})
public class RequestOnRepairController {

    private final RequestOnRepairService requestOnRepairService;

    @Autowired
    public RequestOnRepairController(RequestOnRepairService requestOnRepairService) {
        this.requestOnRepairService = requestOnRepairService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all requests on repair")
    public List<RequestOnRepairDTO> findAll() {
        return requestOnRepairService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create new requests on repair")
    public RestResponse create(@RequestBody @Valid RequestOnRepairDTO requestOnRepairDto) {
        requestOnRepairService.create(requestOnRepairDto);
        return new RestResponse(REQUEST_ON_REPAIR_CREATED);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Delete requests on repair with the specified id")
    public RestResponse delete(@PathVariable Long id) {
        requestOnRepairService.deleteById(id);
        return new RestResponse("id " + id + " deleted");
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Modify requests on repair with the specified id")
    public RestResponse update(@PathVariable Long id, @RequestBody @Valid RequestOnRepairDTO requestOnRepairDto) {
        requestOnRepairService.update(id, requestOnRepairDto);
        return new RestResponse(REQUEST_ON_REPAIR_UPDATED);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get requests on repair with the specified id")
    public RequestOnRepairDTO findById(@PathVariable Long id) {
        return requestOnRepairService.findById(id);
    }
}
