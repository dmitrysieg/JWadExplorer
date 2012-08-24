package ru.doom.wad.logic;

import java.io.*;

public class IWadReader {

	private static final int IWAD = 1145132873;
	
	public Wad read(File file) throws IOException {

		LERandomAccessFile reader = new LERandomAccessFile(file, "r");
		final Wad wad = new Wad();

		try {
			checkSignature(reader);
			final int numlumps = reader.readLEInt();
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
			}

			// read files
			for (int i = 0; i < numlumps; i++) {
				final WadEntry wadEntry = wad.get(i);
				reader.seek(wadEntry.getOffset());
				final byte[] content = new byte[wadEntry.getSize()];
				reader.readFully(content);
				wadEntry.setContent(content);
				System.out.println(new String(wadEntry.getName()));
			}
		} catch (IWadParseException e) {
			e.printStackTrace();
			return null;
		}

		reader.close();
		return wad;
	}

	private void checkSignature(LERandomAccessFile reader) throws IOException, IWadParseException {
		int sign = reader.readLEInt();
		if (sign != IWAD) {
			throw new IWadParseException("Invalid signature");
		}
	}
}
