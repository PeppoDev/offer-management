package com.offer.management.offermanagement.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public UserAlreadyExistsException() {
        super("Usuário já existe");
    }
}
