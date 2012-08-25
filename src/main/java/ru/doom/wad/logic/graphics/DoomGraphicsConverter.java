package ru.doom.wad.logic.graphics;

import com.google.inject.Singleton;
import ru.doom.wad.view.widget.Palette;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

@Singleton
public class DoomGraphicsConverter {

	public Image convertSprite(byte[] imageFile, Palette palette) throws GraphicsParsingException {

		final ContentParser parser = new ContentParser(imageFile);

		final int w = parser.readShort();
		assertPositiveShort(w);
		final int h = parser.readShort();
		assertPositiveShort(h);
		final int loff = parser.readShort();
		final int toff = parser.readShort();

		final int[] columns = parser.readArrayInt(w);
		for (int off : columns) {
			assertIsOffset(off, imageFile.length);
		}

		// starting to draw image
		final BufferedImage image = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
		final WritableRaster raster = image.getRaster();

		for (int x = 0; x < w; x++) {
			parser.seek(columns[x]);
			while (parser.get() != 0xFF) {
				final int startrow = parser.readByte();
				final int numpixels = parser.readByte();
				final int[] pixels = parser.readBytes(numpixels + 2);
				for (int j = 0; j < numpixels; j++) {
					final Color color = palette.get(pixels[j]);
					raster.setPixel(x, startrow + j, new int[]{
							color.getRed(),
							color.getGreen(),
							color.getBlue()
					});
				}
			}
		}
		return image;
	}

	private void assertIsOffset(int off, int size) throws GraphicsParsingException {
		if (off < 0 || off > size) {
			throw new GraphicsParsingException();
		}
	}

	private void assertPositiveShort(int a) throws GraphicsParsingException {
		if (a <= 0 || a > 32767) {
			throw new GraphicsParsingException();
		}
	}
}
