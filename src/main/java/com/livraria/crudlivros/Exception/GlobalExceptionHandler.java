package com.livraria.crudlivros.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> erros = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            erros.put(error.getField(), error.getDefaultMessage());
        });

        return new ResponseEntity<>(erros, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(EscritorNotFoundException.class)
    public ResponseEntity<Object> handleEscritorNotFound(EscritorNotFoundException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(CpfJaCadastradoException.class)
    public ResponseEntity<Object> handleCpfDuplicado(CpfJaCadastradoException ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception ex) {
        Map<String, String> erro = new HashMap<>();
        erro.put("erro", "Erro interno no servidor: " + ex.getMessage());
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }
}
