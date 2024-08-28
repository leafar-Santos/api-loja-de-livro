package com.santos.lojaLivros.services.exceptions;

import java.io.Serializable;

public class ExcecaoIntegridadeDados extends RuntimeException implements Serializable {

    public ExcecaoIntegridadeDados(String message) {
        super(message);
    }

    public ExcecaoIntegridadeDados(String message, Throwable cause) {
        super(message, cause);
    }
}
