package ru.doom.wad.logic.format.wad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.doom.wad.logic.task.LoadFileTask;
import ru.doom.wad.view.Controller;
import ru.doom.wad.view.View;
import ru.doom.wad.view.palette.PaletteReader;

import java.awt.image.ColorModel;
import java.io.File;

@Component
@Scope("prototype")
public class OpenWadTask extends LoadFileTask {

	@Autowired
	private Controller controller;
	@Autowired
	private View view;
	@Autowired
	private PaletteReader paletteReader;
	@Autowired
	private WadUtils wadUtils;

	@Override
	public void load(final File file) throws Exception {
		controller.showProgress();
		final Wad wad = new IWadReader(view.getProgressBar()).read(file);
		controller.setCurrentWad(wad);
		final WadEntry wadEntry = wadUtils.findByName(wad, "PLAYPAL").orElseThrow(() -> new Exception("Empty"));
		final ColorModel palette = paletteReader.readPalette(wadEntry.getContent(), 0);
		controller.processOnLoadWad(palette);
	}

	@Override
	public void onError(Throwable e) {
		controller.showError("Error opening WAD file", e.getLocalizedMessage());
	}
}
