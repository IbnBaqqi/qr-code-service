package com.ibnbaqqi.qrcode;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;


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
    public ResponseEntity<byte[]> qrcode(@RequestParam String content,
                                         @RequestParam(required = false, defaultValue = "250") int size,
                                         @RequestParam(required = false, defaultValue = "png") String type,
                                         @RequestParam(required = false, defaultValue = "L") char correction) {

        if(content.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Contents cannot be null or blank");

        if (size < 150 || size > 350)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Image size must be between 150 and 350 pixels");

        correction = Character.toUpperCase(correction);

        if (!Set.of('L', 'M', 'Q', 'H').contains(correction))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Permitted error correction levels are L, M, Q, H");


        MediaType imageType = switch (type) {
            case "png" -> MediaType.IMAGE_PNG;
            case "jpeg" -> MediaType.IMAGE_JPEG;
            case "gif" -> MediaType.IMAGE_GIF;
            default -> throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Only png, jpeg and gif image types are supported");
        };

        var image = qrcodeService.generateByteImage(content, size, type, correction);

        return ResponseEntity.ok().contentType(imageType).body(image);
    }

}
