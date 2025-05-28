package com.livraria.crudlivros.Exception;

public class EscritorNotFoundException extends RuntimeException {
    public EscritorNotFoundException(String message) {
        super(message);
    }
}

