package ru.rlane.image;

import java.awt.*;
import java.awt.image.BufferedImage;

public final class Tiles {

	public static TexturePaint createChessTileTexture(final int tileSize) {
		BufferedImage bufferedImage = new BufferedImage(tileSize * 2, tileSize * 2, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.setPaint(Color.LIGHT_GRAY);
		g2.fill(new Rectangle(0, 0, tileSize, tileSize));
		g2.fill(new Rectangle(tileSize, tileSize, tileSize, tileSize));
		g2.setPaint(Color.WHITE);
		g2.fill(new Rectangle(0, tileSize, tileSize, tileSize));
		g2.fill(new Rectangle(tileSize, 0, tileSize, tileSize));
		return new TexturePaint(bufferedImage, new Rectangle(tileSize * 2, tileSize * 2));
	}
}
