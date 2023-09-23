package ru.komarov.crudrest.exception;


import static ru.komarov.crudrest.constant.Constant.BY_ID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Long id, String message) {
        super(message + BY_ID + id);
    }
}
