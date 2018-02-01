package ru.doom.wad.view.palette;

import org.springframework.stereotype.Component;

import java.awt.image.ColorModel;
import java.awt.image.IndexColorModel;

@Component
public class PaletteReader {

	public ColorModel readPalette(byte[] paletteFile, int num) {
		return new IndexColorModel(8, 256, paletteFile, num * 256 * 3, false);
	}
}
