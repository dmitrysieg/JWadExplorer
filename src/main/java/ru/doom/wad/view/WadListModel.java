package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.logic.Utils;
import ru.doom.wad.logic.Wad;

import javax.swing.*;
import javax.swing.event.ListDataListener;

@Singleton
public class WadListModel implements ListModel {

	@Inject
	private Utils utils;

	private Wad wad;

	public WadListModel withWad(Wad wad) {
		this.wad = wad;
		return this;
	}

	@Override
	public int getSize() {
		checkWad();
		return wad.size();
	}

	@Override
	public Object getElementAt(int index) {
		checkWad();
		return utils.trimNull(new String(wad.get(index).getName()));
	}

	@Override
	public void addListDataListener(ListDataListener l) {
		//
	}

	@Override
	public void removeListDataListener(ListDataListener l) {
		//
	}

	private void checkWad() {
		if (wad == null) {
			throw new IllegalStateException("wad is not specified");
		}
	}
}
