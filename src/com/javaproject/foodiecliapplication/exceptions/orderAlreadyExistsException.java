package com.javaproject.foodiecliapplication.exceptions;

public class orderAlreadyExistsException extends Exception{
    public orderAlreadyExistsException(String message) {
        super(message);
    }
}
