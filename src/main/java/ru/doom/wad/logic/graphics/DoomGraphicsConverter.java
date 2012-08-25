package ru.doom.wad.logic.graphics;

import com.google.inject.Singleton;

import java.awt.Image;
import java.awt.image.*;

@Singleton
public class DoomGraphicsConverter {

	public Image convertSprite(byte[] imageFile, ColorModel palette) throws GraphicsParsingException {

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
		final byte[] data = new byte[h * w];

		for (int x = 0; x < w; x++) {
			parser.seek(columns[x]);
			while (parser.get() != 0xFF) {
				final int startrow = parser.readByte();
				final int numpixels = parser.readByte();
				final int[] pixels = parser.readBytes(numpixels + 2);
				for (int j = 0; j < numpixels; j++) {
					data[(startrow + j) * w + x] = (byte)pixels[j];
				}
			}
		}
		final DataBuffer dataBuffer = new DataBufferByte(data, h * w);
		final SampleModel sampleModel = new SinglePixelPackedSampleModel(DataBuffer.TYPE_BYTE, w, h, new int[]{0xFF});
		final WritableRaster raster = Raster.createWritableRaster(sampleModel, dataBuffer, null);
		return new BufferedImage(palette, raster, false, null);
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
