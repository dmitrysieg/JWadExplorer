package ru.doom.wad.logic;

import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Component
public class FileController {

	public void saveWadFile(File file, byte[] content) throws IOException {
		if (file != null) {
			FileOutputStream stream = new FileOutputStream(file);
			stream.write(content);
			stream.close();
		}
	}

	public void saveImageFile(File file, RenderedImage image) throws IOException {
		if (file != null) {
			ImageIO.write(image, "gif", file);
		}
	}
}
