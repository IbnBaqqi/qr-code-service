package com.ibnbaqqi.qrcode;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class qrcodeService {

    public byte[] generateImage(int size, String type) {

        var bufferedImage = getBufferedImage(size);
        byte[] bytes;

        try (var byteArray = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, type, byteArray); // writing the image in the PNG format
            bytes = byteArray.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return bytes;
    }

    private static BufferedImage getBufferedImage(int size) {
        var image = new BufferedImage(size, size, 8); // 250x250 pixel image & 8-bit RGB color space
        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);
        return image;
    }
}
