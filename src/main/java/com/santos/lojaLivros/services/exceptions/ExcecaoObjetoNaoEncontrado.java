package com.santos.lojaLivros.services.exceptions;

public class ExcecaoObjetoNaoEncontrado extends RuntimeException{

    public ExcecaoObjetoNaoEncontrado(String message, Throwable cause) {
        super(message, cause);
    }

    public ExcecaoObjetoNaoEncontrado(String message) {
        super(message);
    }
}
