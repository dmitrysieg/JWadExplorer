package ru.doom.wad.logic.format.wad;

import ru.doom.wad.logic.LeftEndRandomAccessFile;

import javax.swing.JProgressBar;
import java.io.File;
import java.io.IOException;

public class IWadReader {

	private static final String IWAD = "IWAD";
	private static final String PWAD = "PWAD";

	private final JProgressBar progressBar;

	public IWadReader(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public Wad read(File file) throws IOException, IWadParseException {

		final LeftEndRandomAccessFile reader = new LeftEndRandomAccessFile(file, "r");
		final Wad wad = new Wad();

		checkSignature(reader);

		final int lumpCount = reader.readLEInt();
		progressBar.setMinimum(0);
		progressBar.setMaximum(lumpCount);
		progressBar.setValue(0);

		final int diroffset = reader.readLEInt();
		reader.seek(diroffset);

		// read directory
		for (int i = 0; i < lumpCount; i++) {
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
		for (int i = 0; i < lumpCount; i++) {
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

	private void checkSignature(final LeftEndRandomAccessFile reader) throws IOException, IWadParseException {
		final String signature = reader.readString4();
		if (!IWAD.equals(signature) && !PWAD.equals(signature)) {
			throw new IWadParseException("Invalid signature");
		}
	}
}
