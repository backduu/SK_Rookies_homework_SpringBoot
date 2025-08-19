package com.packt.myspringbootlab2.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private LocalDateTime timestamp;
}

