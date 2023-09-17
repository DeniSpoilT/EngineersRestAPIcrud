package ru.komarov.crudrest.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashSet;
import java.util.Set;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(HttpStatus httpStatus, MethodArgumentNotValidException exception){
        Set<String> errorsSet = new HashSet<>();

        exception.getBindingResult().getAllErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            errorsSet.add(errorMessage);
        });

        return buildErrorArrayResponse(httpStatus, errorsSet.toArray(new String[0]));
    }

    private static ResponseEntity<Object> buildErrorArrayResponse(HttpStatus httpStatus, String[] message){
        ErrorArrayResponse response = new ErrorArrayResponse(
                httpStatus.value(), httpStatus.getReasonPhrase(), message);
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

    @Getter
    @AllArgsConstructor
    @JsonInclude(JsonInclude.Include.NON_NULL)
    static class ErrorArrayResponse{
        private int status;
        private String error;
        private String[] message;
    }
}
