package ru.doom.wad.logic.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.doom.wad.logic.IWadReader;
import ru.doom.wad.logic.Wad;
import ru.doom.wad.logic.WadEntry;
import ru.doom.wad.logic.WadUtils;
import ru.doom.wad.view.Controller;
import ru.doom.wad.view.View;
import ru.doom.wad.view.palette.PaletteReader;

import java.awt.image.ColorModel;
import java.io.File;

@Component
@Scope("prototype")
public class OpenWadTask implements Runnable {

	@Autowired
	private Controller controller;
	@Autowired
	private View view;
	@Autowired
	private PaletteReader paletteReader;
	@Autowired
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
				final Wad wad = new IWadReader(view.getProgressBar()).read(file);
				controller.setCurrentWad(wad);
				final WadEntry wadEntry = wadUtils.findByName(wad, "PLAYPAL").orElseThrow(() -> new Exception("Empty"));
				final ColorModel palette = paletteReader.readPalette(wadEntry.getContent(), 0);
				controller.processOnLoadWad(palette);
			} catch (Exception e) {
				controller.showError("Error opening WAD file", e.getLocalizedMessage());
			}
		}
	}
}
