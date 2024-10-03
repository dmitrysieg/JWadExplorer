package ru.doom.wad.logic.format.wad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.doom.wad.logic.task.LoadFileTask;
import ru.doom.wad.view.Controller;
import ru.doom.wad.view.View;

import java.io.File;

@Component
@Scope("prototype")
public class OpenWadTask extends LoadFileTask {

	@Autowired private Controller controller;
	@Autowired private View view;

	@Override
	public void load(final File file) throws Exception {
		controller.addEditorTab(file.getAbsolutePath(), file.getName());
		controller.showProgress();

		final Wad wad = new IWadReader(view.getCurrentWorkspace().getProgressBar()).read(file);
		controller.setCurrentWad(wad);
		controller.processOnLoadWad(controller.readPalette(wad));
	}

	@Override
	public void onError(Throwable e) {
		controller.removeEditorTab(controller.getCurrentTab().getAbsolutePath());
		controller.showError("Error opening WAD file", e.getLocalizedMessage());
	}
}
