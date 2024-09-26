package org.animalreg.exceptions;

public class AnimalParseException extends Exception{
    public AnimalParseException(String message) {
        super(message);
    }

    public AnimalParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
