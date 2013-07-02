package ru.doom.wad.view.widget;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class ImagePanel extends JPanel {

	private static final int TILE_SIZE = 5;

	private final TexturePaint texture;
	private Image image;
	
	public void setImage(Image image) {
		this.image = image;
	}

	public ImagePanel() {
		super();
		texture = createTileTexture();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		final int h = g.getClipBounds().height;
		final int w = g.getClipBounds().width;

		if (image != null) {
			g.drawImage(image, 0, 0, w, h, null);
		} else {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(texture);
			g2d.fill(g2d.getClip());
		}
	}

	private static TexturePaint createTileTexture() {
		BufferedImage bufferedImage = new BufferedImage(TILE_SIZE * 2, TILE_SIZE * 2, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.setPaint(Color.GRAY);
		g2.fill(new Rectangle(0, 0, TILE_SIZE, TILE_SIZE));
		g2.fill(new Rectangle(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE));
		g2.setPaint(Color.WHITE);
		g2.fill(new Rectangle(0, TILE_SIZE, TILE_SIZE, TILE_SIZE));
		g2.fill(new Rectangle(TILE_SIZE, 0, TILE_SIZE, TILE_SIZE));
		return new TexturePaint(bufferedImage, new Rectangle(TILE_SIZE * 2, TILE_SIZE * 2));
	}
}
