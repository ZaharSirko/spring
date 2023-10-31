package com.example.qspring;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.jtransforms.dct.DoubleDCT_2D;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
@SpringBootTest
class QspringApplicationTests {

	public static void main(String[] args) {
        String coverImageFileName = "D://Microsoft VS Code//projects//__k3//sec//screen.jpg";
        String secretMessage = "Message";
        String stegoImageFileName = "D://Microsoft VS Code//qspring//src//test//java//com//example//qspring//stere.jpg";;

        try {
            BufferedImage coverImage = ImageIO.read(new File(coverImageFileName));
			BufferedImage stegoImage = hideMessage(coverImage, secretMessage);

			ImageIO.write(stegoImage, "jpg", new File(stegoImageFileName));

			System.out.println("Complete");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static BufferedImage hideMessage(BufferedImage coverImage, String message) {
		int width = coverImage.getWidth();
		int height = coverImage.getHeight();
		BufferedImage stegoImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	
		byte[] messageBytes = message.getBytes();
		int messageLength = messageBytes.length * 8;
		int currentMessageBitIndex = 0;
	
		DoubleDCT_2D dct = new DoubleDCT_2D(8, 8);
	
		for (int y = 0; y < height; y += 8) {
			for (int x = 0; x < width; x += 8) {
				if (x + 8 <= width && y + 8 <= height) { 
					double[][] Y = new double[8][8];
					int[][] reds = new int[8][8];
					int[][] greens = new int[8][8];
					int[][] blues = new int[8][8];
	
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							int pixel = coverImage.getRGB(x + j, y + i);
							reds[i][j] = (pixel >> 16) & 0xFF;
							greens[i][j] = (pixel >> 8) & 0xFF;
							blues[i][j] = pixel & 0xFF;
							Y[i][j] = 0.299 * reds[i][j] + 0.587 * greens[i][j] + 0.114 * blues[i][j];
						}
					}
	
					dct.forward(Y, true);
	
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							if (currentMessageBitIndex < messageLength) {
								int bitToHide = (messageBytes[currentMessageBitIndex / 8] >> (7 - (currentMessageBitIndex % 8))) & 1;
								Y[i][j] = (Y[i][j] / 2) * 2 + bitToHide; 
								currentMessageBitIndex++;
							}
						}	
					}
	
					dct.inverse(Y, true);
	
					for (int i = 0; i < 8; i++) {
						for (int j = 0; j < 8; j++) {
							int newRed = reds[i][j];
							int newGreen = greens[i][j];
							int newBlue = blues[i][j];
	
							newRed = Math.min(255, Math.max(0, newRed));
							newGreen = Math.min(255, Math.max(0, newGreen));
							newBlue = Math.min(255, Math.max(0, newBlue));
	
							int newPixel = (newRed << 16) | (newGreen << 8) | newBlue;
							stegoImage.setRGB(x + j, y + i, newPixel);
						}
					}
				}
			}
		}
	
		return stegoImage;
	}
	
	
}


