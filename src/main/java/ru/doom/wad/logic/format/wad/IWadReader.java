package ru.doom.wad.logic.format.wad;

import ru.doom.wad.logic.LERandomAccessFile;

import javax.swing.*;
import java.io.*;

public class IWadReader {

	private static final int IWAD = 1145132873;

	private final JProgressBar progressBar;

	public IWadReader(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public Wad read(File file) throws IOException, IWadParseException {

		LERandomAccessFile reader = new LERandomAccessFile(file, "r");
		final Wad wad = new Wad();

		checkSignature(reader);

		final int numlumps = reader.readLEInt();
		progressBar.setMinimum(0);
		progressBar.setMaximum(numlumps);
		progressBar.setValue(0);

		final int diroffset = reader.readLEInt();
		reader.seek(diroffset);

		// read directory
		for (int i = 0; i < numlumps; i++) {
			WadEntry wadEntry = new WadEntry();
			wadEntry.setOffset(reader.readLEInt());
			wadEntry.setSize(reader.readLEInt());
			final byte[] name = new byte[8];
			reader.readFully(name);
			wadEntry.setName(name);
			wad.add(wadEntry);
			progressBar.setValue(i+1);
		}

		// read files
		for (int i = 0; i < numlumps; i++) {
			final WadEntry wadEntry = wad.get(i);
			reader.seek(wadEntry.getOffset());
			final byte[] content = new byte[wadEntry.getSize()];
			reader.readFully(content);
			wadEntry.setContent(content);
		}

		reader.close();
		progressBar.setValue(0);
		return wad;
	}

	private void checkSignature(LERandomAccessFile reader) throws IOException, IWadParseException {
		int sign = reader.readLEInt();
		if (sign != IWAD) {
			throw new IWadParseException("Invalid signature");
		}
	}
}
