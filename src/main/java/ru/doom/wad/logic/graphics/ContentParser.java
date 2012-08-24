package ru.doom.wad.logic.graphics;

public class ContentParser {

	private final byte[] content;
	private int cursor;

	public ContentParser(byte[] content) {
		this.content = content;
	}

	public int get() {
		return content[cursor] & 0xFF;
	}
	
	public int readByte() {
		return content[cursor++] & 0xFF;
	}

	public void seek(int offset) {
		cursor = offset;
	}
	
	public int[] readBytes(int length) {
		final int[] array = new int[length];
		for (int i = 0; i < length; i++) {
			array[i] = readByte();
		}
		return array;
	}
	
	public int readShort() {
		return readByte() | (readByte() << 8);
	}
	
	public int readInt() {
		return readByte() |(readByte() << 8)
				|(readByte() << 16)
				|(readByte() << 24);
	}

	public int[] readArrayInt(int length) {
		final int[] array = new int[length];
		for (int i = 0; i < length; i++) {
			array[i] = readInt();
		}
		return array;
	}
}
