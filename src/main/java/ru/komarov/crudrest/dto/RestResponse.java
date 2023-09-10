package ru.komarov.crudrest.dto;

import io.swagger.v3.oas.annotations.Hidden;

@Hidden
public record RestResponse(Object result) {}
