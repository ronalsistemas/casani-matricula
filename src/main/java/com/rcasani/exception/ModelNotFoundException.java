package com.rcasani.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND) : lo ocultamos porque las respuesta ya se está controlando en 'GlobalErrorHandler'
public class ModelNotFoundException extends RuntimeException { //Errores en tiempo de ejecución 'RuntimeException'

    //Lanza mensajes personalizados al momento que se hace la excepcion
    public ModelNotFoundException(String message) {
        super(message);
    }
}
