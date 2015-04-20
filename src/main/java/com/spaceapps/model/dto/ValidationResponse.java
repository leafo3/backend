package com.spaceapps.model.dto;

import com.spaceapps.model.ValidationError;

import java.util.List;

/**
 * Created by Alberto Rubalcaba on 4/11/2015.
 */
public class ValidationResponse {
    private List<ValidationError> errors;

    public ValidationResponse(List<ValidationError> errors){
        this.errors = errors;
    }

    public List<ValidationError> getErrors(){
        return this.errors;
    }
}
