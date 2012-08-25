package ru.doom.wad.view.listeners;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.logic.task.TaskProvider;
import ru.doom.wad.view.DialogManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@Singleton
public class MainMenuActionListener implements ActionListener {

	@Inject
	private DialogManager dialogManager;
	@Inject
	private TaskProvider taskProvider;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			if ("Open".equals(e.getActionCommand())) {
				final File file = dialogManager.selectOpenFile();
				new Thread(taskProvider.get().withFile(file)).start();
			}
		}		
	}
}
