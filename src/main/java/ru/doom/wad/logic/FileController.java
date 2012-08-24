package ru.doom.wad.logic;

import com.google.inject.Singleton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Singleton
public class FileController {

	public void saveWadFile(File file, byte[] content) throws IOException {
		if (file != null) {
			FileOutputStream stream = new FileOutputStream(file);
			stream.write(content);
			stream.close();
		}
	}
}
