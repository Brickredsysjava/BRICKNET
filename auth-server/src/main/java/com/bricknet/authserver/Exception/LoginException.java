package com.bricknet.authserver.Exception;

public class LoginException extends Exception{
    public LoginException() {
    }

    public LoginException(String message) {
        super(message);
    }
}
