package org.animalreg.exceptions;

public class RuleProcessingException extends Exception{
    public RuleProcessingException(String message) {
        super(message);
    }

    public RuleProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
