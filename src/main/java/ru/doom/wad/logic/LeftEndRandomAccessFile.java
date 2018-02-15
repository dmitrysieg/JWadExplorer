package ru.doom.wad.logic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class LeftEndRandomAccessFile extends RandomAccessFile {

	public LeftEndRandomAccessFile(File file, String mode) throws FileNotFoundException {
		super(file, mode);
	}

	public int readLEInt() throws IOException {
		final int c1 = readByte() & 0xff;
		final int c2 = readByte() & 0xff;
		final int c3 = readByte() & 0xff;
		final int c4 = readByte() & 0xff;
		return c1 | (c2 << 8) | (c3 << 16) | (c4 << 24);
	}
}
