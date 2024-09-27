package org.animalreg.exceptions;

public class AnimalParseException extends RuntimeException{
    public AnimalParseException(String message) {
        super(message);
    }

    public AnimalParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
