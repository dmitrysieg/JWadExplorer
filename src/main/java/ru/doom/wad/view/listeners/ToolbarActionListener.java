package ru.doom.wad.view.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.view.Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class ToolbarActionListener implements ActionListener {

	@Autowired
	private Controller controller;

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Save file".equals(e.getActionCommand()) && controller.isAllowedSaveFile()) {
			controller.controlSaveWadFile();
		} else if ("Save image".equals(e.getActionCommand()) && controller.isAllowedSaveImage()) {
			controller.saveCurrentImage();
		}
	}
}
