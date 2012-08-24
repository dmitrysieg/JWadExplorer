package ru.doom.wad.logic.task;

import com.google.inject.Inject;
import ru.doom.wad.logic.IWadReader;
import ru.doom.wad.logic.Wad;
import ru.doom.wad.logic.WadUtils;
import ru.doom.wad.view.Controller;
import ru.doom.wad.view.WadCellRenderer;
import ru.doom.wad.view.WadListModel;
import ru.doom.wad.view.palette.PaletteReader;
import ru.doom.wad.view.widget.Palette;

import javax.swing.*;
import java.io.File;

public class OpenWadTask implements Runnable {

	@Inject
	private Controller controller;
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
				Wad wad = new IWadReader(controller.getProgressBar()).read(file);
				Palette palette = paletteReader.readPalette(wadUtils.findByName(wad, "PLAYPAL").getContent(), 0);
				controller.getPalettePanel().setPalette(palette);
				controller.getPalettePanel().repaint();
				controller.getList().setCellRenderer(new WadCellRenderer().wad(wad));
				controller.getList().setModel(wadListModel.withWad(wad));
				controller.getListPane().doLayout();
			} catch (Exception e) {
				JOptionPane.showMessageDialog(
						controller.getFrame(),
						e.getLocalizedMessage(),
						"Error opening WAD file",
						JOptionPane.ERROR_MESSAGE
				);
			}
		}
	}
}
