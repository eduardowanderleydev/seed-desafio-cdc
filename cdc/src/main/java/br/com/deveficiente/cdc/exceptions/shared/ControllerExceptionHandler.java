package br.com.deveficiente.cdc.exceptions.shared;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.Instant.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;

// 4 points of cognitive load
@RestControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public StandardError handleValidationError(ResourceNotFoundException e, HttpServletRequest request) {
        return new StandardError(now(), NOT_FOUND.value(), e.getMessage(), request.getRequestURI(), "Resource not found");
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationError handleValidationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        return new ValidationError(now(), NOT_FOUND.value(), e.getMessage(), request.getRequestURI(), "Invalid argument(s)",
                e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(err -> new FieldMessage(err.getField(), err.getDefaultMessage())).toList());
    }
}
