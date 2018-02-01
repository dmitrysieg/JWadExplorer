package ru.doom.wad.view.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.logic.task.TaskProvider;
import ru.doom.wad.view.DialogManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@Component
public class MainMenuActionListener implements ActionListener {

	@Autowired
	private DialogManager dialogManager;
	@Autowired
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
