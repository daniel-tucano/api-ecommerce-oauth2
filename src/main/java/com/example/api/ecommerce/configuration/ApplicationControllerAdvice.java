package com.example.api.ecommerce.configuration;

import com.example.api.ecommerce.response.ErrorReponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(EntityNotFoundException.class)
    private ResponseEntity<ErrorReponse> retornaErroEntidadeNaoEncontrada(EntityNotFoundException exception) {
        return ResponseEntity.badRequest().body(new ErrorReponse("Entidade não encontrada", exception.getMessage()));
    }

}
