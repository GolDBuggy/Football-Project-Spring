package com.nar.halisaha.ExceptionHandle;

public class TeamException extends RuntimeException{
    public TeamException(String message) {
        super(message);
    }

    public TeamException(String message, Throwable cause) {
        super(message, cause);
    }
}
