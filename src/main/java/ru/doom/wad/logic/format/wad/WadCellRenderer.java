package ru.doom.wad.logic.format.wad;

import javax.swing.*;
import java.awt.*;

public class WadCellRenderer extends DefaultListCellRenderer {

	private Wad wad;
	
	public WadCellRenderer wad(Wad wad) {
		this.wad = wad;
		return this;
	}

	@Override
	public Component getListCellRendererComponent(
			JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus
	) {
		super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
		if (isSelected) {
			setBackground(Color.GRAY);
		} else {
			setBackground(wad.get(index).getSize() == 0 ? Color.YELLOW : Color.WHITE);
		}
		return this;
	}
}
