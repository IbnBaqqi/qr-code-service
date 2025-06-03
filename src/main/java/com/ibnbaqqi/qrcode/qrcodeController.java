package com.ibnbaqqi.qrcode;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class qrcodeController {

    private final qrcodeService qrcodeService;

    public qrcodeController(qrcodeService qrcodeService) {
        this.qrcodeService = qrcodeService;
    }

    @GetMapping("/health")
    public ResponseEntity<Void> health() {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/qrcode")
    public ResponseEntity<byte[]> qrcode() {

        var image = qrcodeService.generateImage();

        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(image);
    }
}
