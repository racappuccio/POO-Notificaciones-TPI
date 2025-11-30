package com.example.TPI_POO_NOTIFICACIONES.Exceptions;

// cuando no se encuentra un recurso en la BD
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
