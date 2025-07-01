package com.will.caleb.business.controller;

import com.will.caleb.business.exception.CustomException;
import com.will.caleb.business.exception.ErrorResponse;
import com.will.caleb.business.model.entity.AbstractGenericEntity;
import com.will.caleb.business.model.records.converters.GenericConverter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public abstract class AbstractController extends GenericConverter {

    @ExceptionHandler(value = CustomException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleCustomException(CustomException ex) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
    }

}
