package com.ibnbaqqi.qrcode;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class qrcodeController {
    @GetMapping("/health")
    public ResponseEntity<Void> health() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/qrcode")
    public ResponseEntity<Void> qrcode() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
