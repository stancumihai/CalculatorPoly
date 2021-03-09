package com.company.exception;

public class NotValidDataEntered extends RuntimeException{

    public NotValidDataEntered() {
    }

    public NotValidDataEntered(String message) {
        super(message);
    }
}
