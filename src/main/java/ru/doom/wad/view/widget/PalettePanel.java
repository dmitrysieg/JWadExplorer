package ru.doom.wad.view.widget;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class PalettePanel extends JPanel {

	private Image paletteImage;
	private WritableRaster raster;

	public PalettePanel() {
		this.paletteImage = null;
		this.raster = null;
	}

	public void init() {
		final byte[] data = new byte[256];
		for (int i = 0; i < 256; i++) {
			data[i] = (byte)i;
		}
		final DataBuffer dataBuffer = new DataBufferByte(data, 256);
		final SampleModel sampleModel = new SinglePixelPackedSampleModel(
				DataBuffer.TYPE_BYTE, 16, 16, new int[]{0xFF}
		);
		raster = Raster.createWritableRaster(
				sampleModel, dataBuffer, null
		);
	}

	public void setPalette(ColorModel palette) {
		if (raster == null) {
			throw new IllegalStateException("Palette panel is not initialized");
		}
		paletteImage = new BufferedImage(palette, raster, false, null);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (raster != null && paletteImage != null) {
			final int h = g.getClipBounds().height;
			final int w = g.getClipBounds().width;

			g.drawImage(paletteImage, 0, 0, w, h, null);
		}
	}
}
