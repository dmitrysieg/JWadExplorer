package ru.doom.wad.logic.format.wad;

public class WadEntry {

	private int offset;
	private int size;
	private byte[] name;
	private byte[] content;

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public byte[] getName() {
		return name;
	}

	public void setName(byte[] name) {
		this.name = name;
	}

	public byte[] getContent() {
		return content;
	}

	public void setContent(byte[] content) {
		this.content = content;
	}
}
