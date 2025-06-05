package com.ibnbaqqi.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

@Service
public class qrcodeService {

    public byte[] generateByteImage(String content, int size, String type) {

        var bufferedImage = getBufferedImage(size);
        var image = generateQR(bufferedImage, content, size);
        byte[] bytes;

        try (var byteArray = new ByteArrayOutputStream()) {
            ImageIO.write(image, type, byteArray); // writing the image in the given format(png, gif, jpeg)
            bytes = byteArray.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }

        return bytes;
    }

    public BufferedImage generateQR(BufferedImage image, String data, int size) {
        QRCodeWriter writer = new QRCodeWriter();
        try {
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, size, size); //encode data in bitMatrix
            image = MatrixToImageWriter.toBufferedImage(bitMatrix);
        } catch (WriterException e) {
            throw new RuntimeException(e.getMessage());
        }
        return image;
    }

    private static BufferedImage getBufferedImage(int size) {
        var image = new BufferedImage(size, size, 8); // 250x250 pixel image & 8-bit RGB color space
        Graphics2D g = image.createGraphics();

        g.setColor(Color.WHITE);
        g.fillRect(0, 0, size, size);
        return image;
    }
}
