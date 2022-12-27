package br.com.deveficiente.cdc.exceptions.shared;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static java.time.Instant.now;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

// 5 points of cognitive load
@RestControllerAdvice
public class ControllerExceptionHandler {

    private MessageSource messageSource;

    public ControllerExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ResponseStatus(NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public StandardError handleValidationError(ResourceNotFoundException e, HttpServletRequest request) {
        return new StandardError(now(), NOT_FOUND.value(), e.getMessage(), request.getRequestURI(), "Resource not found");
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ValidationError handleValidationError(MethodArgumentNotValidException e, HttpServletRequest request) {
        return new ValidationError(now(), BAD_REQUEST.value(), e.getMessage(), request.getRequestURI(), "Invalid argument(s)",
                e.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .map(err -> new FieldMessage(err.getField(), getErrorMessage(err))).toList());
    }

    private String getErrorMessage(ObjectError error) {
        return messageSource.getMessage(error, LocaleContextHolder.getLocale());
    }
}
