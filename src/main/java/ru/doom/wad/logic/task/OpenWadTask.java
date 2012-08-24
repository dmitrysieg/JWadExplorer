package ru.doom.wad.logic.task;

import ru.doom.wad.logic.IWadReader;
import ru.doom.wad.logic.Wad;
import ru.doom.wad.view.Controller;
import ru.doom.wad.view.WadCellRenderer;
import ru.doom.wad.view.WadListModel;

import javax.swing.*;
import java.io.File;

public class OpenWadTask implements Runnable {

	private final Controller controller;
	private final File file;

	public OpenWadTask(Controller controller, File file) {
		this.controller = controller;
		this.file = file;
	}

	@Override
	public void run() {
		try {
			Wad wad = new IWadReader(controller.getProgressBar()).read(file);
			controller.getList().setCellRenderer(new WadCellRenderer().wad(wad));
			controller.getList().setModel(new WadListModel(wad));
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
