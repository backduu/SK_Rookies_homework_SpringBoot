package com.packt.myspringbootlab2.Exception;


import lombok.Getter;

@Getter
public class ErrorObject {

    private String code;
    private String message;

    public ErrorObject(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

