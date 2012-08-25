package ru.doom.wad.logic.task;

import com.google.inject.Inject;
import ru.doom.wad.logic.IWadReader;
import ru.doom.wad.logic.Wad;
import ru.doom.wad.logic.WadUtils;
import ru.doom.wad.view.Controller;
import ru.doom.wad.view.View;
import ru.doom.wad.view.WadCellRenderer;
import ru.doom.wad.view.WadListModel;
import ru.doom.wad.view.palette.PaletteReader;
import ru.doom.wad.view.widget.Palette;

import java.io.File;

public class OpenWadTask implements Runnable {

	@Inject
	private Controller controller;
	@Inject
	private View view;
	@Inject
	private WadListModel wadListModel;
	@Inject
	private PaletteReader paletteReader;
	@Inject
	private WadUtils wadUtils;

	private File file;

	public OpenWadTask withFile(File file) {
		this.file = file;
		return this;
	}

	@Override
	public void run() {
		if (file != null) {
			try {
				controller.showProgress();
				Wad wad = new IWadReader(view.getProgressBar()).read(file);
				Palette palette = paletteReader.readPalette(wadUtils.findByName(wad, "PLAYPAL").getContent(), 0);
				view.getPalettePanel().setPalette(palette);
				view.getPalettePanel().repaint();
				view.getList().setCellRenderer(new WadCellRenderer().wad(wad));
				view.getList().setModel(wadListModel.withWad(wad));
				view.getListPane().doLayout();
			} catch (Exception e) {
				controller.showError("Error opening WAD file", e.getLocalizedMessage());
			}
		}
	}
}
