package com.santos.lojaLivros.Resources.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
public class ValidationError extends StandardError {

    private List<FieldMessage> erros = new ArrayList<>();


    //Construtor da super classe
    public ValidationError(long timestamp, Integer status, String error) {
        super(timestamp, status, error);
    }

    public void addError(String fieldName, String message){
        this.erros.add(new FieldMessage(fieldName, message));
    }

}
