package com.ibnbaqqi.qrcode;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, String>> handleResponseException(ResponseStatusException ex) {
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("error", ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(errorBody);
    }
}
