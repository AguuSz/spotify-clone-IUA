package main.java.exception;

public class ForbiddenAccessException extends Exception{
    public ForbiddenAccessException (String message) {
        super(message);
    }
}