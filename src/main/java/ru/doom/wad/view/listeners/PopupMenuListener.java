package ru.doom.wad.view.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.view.Commands;
import ru.doom.wad.view.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class PopupMenuListener implements ActionListener {

	@Autowired
	private Controller controller;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (Commands.SAVE_FILE.equals(e.getActionCommand())) {
			controller.controlSaveWadFile();
		}
	}
}
