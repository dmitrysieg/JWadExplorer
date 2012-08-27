package ru.doom.wad.view.widget;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.net.URL;

public class ImagePanel extends JPanel {

	private static final int TILE_SIZE = 10;

	private final Image tile;
	private Image image;
	
	public void setImage(Image image) {
		this.image = image;
	}

	public ImagePanel() {
		super();
		final URL url = this.getClass().getResource("tile.png");
		tile = Toolkit.getDefaultToolkit().createImage(url);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		final int h = g.getClipBounds().height;
		final int w = g.getClipBounds().width;

		if (image != null) {
			g.drawImage(image, 0, 0, w, h, null);
		} else {
			for (int j = 0; j < h; j += TILE_SIZE) {
				for (int i = 0; i < w; i += TILE_SIZE) {
					g.drawImage(tile, i, j, TILE_SIZE, TILE_SIZE, null);
				}
			}			
		}
	}
}
