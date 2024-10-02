package ru.doom.wad.logic.format.wad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.event.ListDataListener;

@Component
public class WadListModelFactory {

	@Autowired
	private WadUtils wadUtils;

	public WadListModel withWad(Wad wad) {
		return new WadListModel(wad);
	}

	public class WadListModel implements ListModel<String> {

		private Wad wad;

		public WadListModel(Wad wad) {
			this.wad = wad;
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
		public String getElementAt(int index) {
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
}
