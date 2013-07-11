package ru.doom.wad.view.widget;

import ru.rlane.image.Tiles;
import ru.rlane.image.resize.Geometry;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

	private static final int TILE_SIZE = 5;

	private static TexturePaint texture = Tiles.createChessTileTexture(TILE_SIZE);
	private Image image;
	
	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		final int h = g.getClipBounds().height;
		final int w = g.getClipBounds().width;

		if (image != null) {
			final Rectangle rect = Geometry.getResizedImageRect(w, h, image.getWidth(null), image.getHeight(null));
			g.drawImage(
					image,
					rect.x,
					rect.y,
					rect.width,
					rect.height,
					null
			);
		} else {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(texture);
			g2d.fill(g2d.getClip());
		}
	}
}
