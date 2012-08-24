package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.logic.WadUtils;
import ru.doom.wad.logic.Wad;

import javax.swing.*;
import javax.swing.event.ListDataListener;

@Singleton
public class WadListModel implements ListModel {

	@Inject
	private WadUtils wadUtils;

	private Wad wad;

	public WadListModel withWad(Wad wad) {
		this.wad = wad;
		return this;
	}
	
	public Wad getWad() {
		return wad;
	}

	@Override
	public int getSize() {
		checkWad();
		return wad.size();
	}

	@Override
	public Object getElementAt(int index) {
		checkWad();
		return wadUtils.trimNull(new String(wad.get(index).getName()));
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
