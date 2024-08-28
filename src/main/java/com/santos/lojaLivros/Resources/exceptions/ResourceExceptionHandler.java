package com.santos.lojaLivros.Resources.exceptions;
import com.santos.lojaLivros.services.exceptions.ExcecaoIntegridadeDados;
import com.santos.lojaLivros.services.exceptions.ExcecaoObjetoNaoEncontrado;
import jakarta.servlet.ServletRequest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(ExcecaoObjetoNaoEncontrado.class)
    public ResponseEntity <StandardError> objetoNaoEncontrado(ExcecaoObjetoNaoEncontrado e, ServletRequest request){
        StandardError error =  new StandardError(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(ExcecaoIntegridadeDados.class)
    public ResponseEntity <StandardError> dataIntegrationViolation(DataIntegrityViolationException e, ServletRequest request){
        StandardError error =  new StandardError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity <StandardError> validationErros(MethodArgumentNotValidException e, ServletRequest request){
        ValidationError error =  new ValidationError(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Erro na validação dos campos");

        for(FieldError x : e.getBindingResult().getFieldErrors()){
            error.addError(x.getField(),x.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
