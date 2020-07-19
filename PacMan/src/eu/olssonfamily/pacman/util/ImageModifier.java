package eu.olssonfamily.pacman.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.RescaleOp;

public class ImageModifier {

	public static Image rotate(Image image, double degrees) {

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bufferedImage.createGraphics();
		bGr.drawImage(image, 0, 0, null);
		bGr.dispose();

		final double rads = Math.toRadians(degrees);
		final double sin = Math.abs(Math.sin(rads));
		final double cos = Math.abs(Math.cos(rads));
		final int w = (int) Math.floor(bufferedImage.getWidth() * cos + bufferedImage.getHeight() * sin);
		final int h = (int) Math.floor(bufferedImage.getHeight() * cos + bufferedImage.getWidth() * sin);
		final BufferedImage rotatedImage = new BufferedImage(w, h, bufferedImage.getType());
		final AffineTransform at = new AffineTransform();
		at.translate(w / 2, h / 2);
		at.rotate(rads, 0, 0);
		at.translate(-bufferedImage.getWidth() / 2, -bufferedImage.getHeight() / 2);
		final AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		rotateOp.filter(bufferedImage, rotatedImage);

		return rotatedImage;
	}

	public static Image setBrightness(Image image, float brightness) {

		BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),
				BufferedImage.TYPE_INT_ARGB);

		Graphics2D bGr = bufferedImage.createGraphics();
		bGr.drawImage(image, 0, 0, null);
		bGr.dispose();
		
		RescaleOp rescaleOp = new RescaleOp(brightness, 1, null);
		rescaleOp.filter(bufferedImage, bufferedImage); // Source and destination are the same.

		return bufferedImage;
		
	}

}
