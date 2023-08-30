package ru.komarov.crudrest.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    protected ResponseEntity<Object> handleEngineerNotFound(NotFoundException exception){
        return buildErrorResponse(HttpStatus.BAD_REQUEST, exception.getMessage());
    }

    private static ResponseEntity<Object> buildErrorResponse(HttpStatus httpStatus, String message){
        ErrorResponse response = new ErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), message);
        return ResponseEntity.status(httpStatus.value()).body(response);
    }
    @Getter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class ErrorResponse{
        private int status;
        private String error;
        private String message;
    }
}
