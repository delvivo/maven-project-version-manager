package it.fdv.mvn.exception;

public class NoBooleanValueException extends Exception {

    public NoBooleanValueException() {
        super("The parameter value must be 'true' or 'false'");
    }
}
