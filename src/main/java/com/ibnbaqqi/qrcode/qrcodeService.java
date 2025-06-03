package com.ibnbaqqi.qrcode;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class qrcodeService {

    public byte[] generateImage() {

        var bufferedImage = getBufferedImage();
        byte[] bytes;

        try (var byteArray = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", byteArray); // writing the image in the PNG format
            bytes = byteArray.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return bytes;
    }

    private static BufferedImage getBufferedImage() {
        var image = new BufferedImage(250, 250, 8); // 250x250 pixel image & 8-bit RGB color space
        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 250, 250);
        return image;
    }
}
