package ru.damir.stock.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.damir.stock.controller.exception.MyException;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@ControllerAdvice
public class ExceptionHandlerController extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Set<String> errors = ex.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());

        log.error("Controller validation failed to {}", errors);
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Обработка исключения MyException<br>
     *
     * @param ex Исключение при работе с товаром
     */
    @ExceptionHandler(value = {MyException.class})
    public ResponseEntity<Object> handleIllegalArgumentExceptions(Exception ex) {
        String message = ex.getMessage();

        return ResponseEntity.internalServerError().body(message);
    }


}
