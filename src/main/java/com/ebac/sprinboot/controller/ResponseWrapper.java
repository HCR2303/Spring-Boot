package com.ebac.sprinboot.controller;

import lombok.Data;
import org.springframework.http.ResponseEntity;

@Data
public class ResponseWrapper<T> {
    private boolean success;
    private String message;
    private ResponseEntity<T> response;

    public ResponseWrapper(boolean success, String message, ResponseEntity<T> response) {
        this.success = success;
        this.message = message;
        this.response = response;
    }


}
