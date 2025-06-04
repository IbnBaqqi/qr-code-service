package com.ibnbaqqi.qrcode;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Set;

@Service
public class qrcodeService {

    public byte[] generateImage(int size, String type) {

        if (size < 150 || size > 350)
            throw new SizeException();
        if (!Set.of("png", "jpeg", "gif").contains(type))
            throw new TypeException();

        var bufferedImage = getBufferedImage(size);
        byte[] bytes;

        try (var byteArray = new ByteArrayOutputStream()) {
            ImageIO.write(bufferedImage, "png", byteArray); // writing the image in the PNG format
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
