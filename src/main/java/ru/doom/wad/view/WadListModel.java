package ru.doom.wad.view;

import ru.doom.wad.logic.Wad;

import javax.swing.*;
import javax.swing.event.ListDataListener;

public class WadListModel implements ListModel {

	private Wad wad;

	public WadListModel(Wad wad) {
		this.wad = wad;
	}

	@Override
	public int getSize() {
		return wad.size();
	}

	@Override
	public Object getElementAt(int index) {
		return new String(wad.get(index).getName());
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		//
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		//
	}
}
