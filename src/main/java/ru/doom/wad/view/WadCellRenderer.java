package ru.doom.wad.view;

import ru.doom.wad.logic.Wad;

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
		setBackground(wad.get(index).getSize() == 0 ? Color.YELLOW : Color.WHITE);
		return this;
	}
}
