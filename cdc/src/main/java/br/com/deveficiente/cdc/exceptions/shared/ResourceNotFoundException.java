package br.com.deveficiente.cdc.exceptions.shared;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
