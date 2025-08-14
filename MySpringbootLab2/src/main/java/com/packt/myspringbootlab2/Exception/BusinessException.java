package com.packt.myspringbootlab2.Exception;


public class BusinessException extends RuntimeException {

    private final ErrorObject errorObject;

    public BusinessException(ErrorObject errorObject) {
        super(errorObject.getMessage());
        this.errorObject = errorObject;
    }

    public ErrorObject getErrorObject() {
        return errorObject;
    }
}

