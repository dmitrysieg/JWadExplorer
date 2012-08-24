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

		BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = image.getRaster();
		Graphics imageGraphics = image.getGraphics();

		final int h = g.getClipBounds().height;
		final int w = g.getClipBounds().width;
		
		if (palette != null) {
			for (int y = 0; y < 16; y++) {
				for (int x = 0; x < 16; x++) {
					/*g.setColor(palette.get(y * 16 + x));
					g.fillRect(x * 16, y * 16, w, h);*/
					Color color = palette.get(y * 16 + x);
					raster.setPixel(x, y, new int[]{color.getRed(), color.getGreen(), color.getBlue()});
				}
			}
			g.drawImage(image, 0, 0, w, h, null);
		}
	}
}
