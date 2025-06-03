package com.ibnbaqqi.qrcode;

import org.springframework.stereotype.Service;

import java.awt.*;
import java.awt.image.BufferedImage;

@Service
public class qrcodeService {

    public BufferedImage generateImage() {
        var image = new BufferedImage(250, 250, 8);
        Graphics2D g = image.createGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, 250, 250);
        return image;
    }
}
