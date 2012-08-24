package ru.doom.wad.view.palette;

import com.google.inject.Singleton;
import ru.doom.wad.view.widget.Palette;

import java.awt.Color;

@Singleton
public class PaletteReader {

	public Palette readPalette(byte[] paletteFile, int num) {
		final Palette palette = new Palette();
		for (int i = 0; i < 256; i++) {
			palette.add(new Color(
					paletteFile[3 * 256 * num + i * 3 + 0] & 0xff,
					paletteFile[3 * 256 * num + i * 3 + 1] & 0xff,
					paletteFile[3 * 256 * num + i * 3 + 2] & 0xff
			));
		}
		return palette;
	}
}
