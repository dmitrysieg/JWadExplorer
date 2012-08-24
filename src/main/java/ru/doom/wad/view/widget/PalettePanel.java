package ru.doom.wad.view.widget;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class PalettePanel extends JPanel {

	private Palette palette;

	public void setPalette(Palette palette) {
		this.palette = palette;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		final BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		final WritableRaster raster = image.getRaster();

		final int h = g.getClipBounds().height;
		final int w = g.getClipBounds().width;
		
		if (palette != null) {
			for (int y = 0; y < 16; y++) {
				for (int x = 0; x < 16; x++) {
					Color color = palette.get(y * 16 + x);
					raster.setPixel(x, y, new int[]{color.getRed(), color.getGreen(), color.getBlue()});
				}
			}
			g.drawImage(image, 0, 0, w, h, null);
		}
	}
}
