package com.ibnbaqqi.qrcode;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


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
    public ResponseEntity<byte[]> qrcode(@RequestParam int size, @RequestParam String type) {

        MediaType imageType = switch (type) {
            case "png" -> MediaType.IMAGE_PNG;
            case "jpeg" -> MediaType.IMAGE_JPEG;
            case "gif" -> MediaType.IMAGE_GIF;
            default -> null;
        };

        var image = qrcodeService.generateImage(size, type);

        return ResponseEntity.ok().contentType(imageType).body(image);
    }

    @ExceptionHandler(SizeException.class)
    public ResponseEntity<?> handleSizeException() {
        return ResponseEntity.badRequest().body(Map.of("error", "Image size must be between 150 and 350 pixels"));
    }

    @ExceptionHandler(TypeException.class)
    public ResponseEntity<?> handleTypeException() {
        return ResponseEntity.badRequest().body(Map.of("error", "Only png, jpeg and gif image types are supported"));
    }
}
