package com.example.TPI_POO_NOTIFICACIONES.Exceptions;

// cuando hay un error de validación
public class BadRequestException extends RuntimeException {
    public BadRequestException(String mensaje) {
        super(mensaje);
    }
}
