package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Singleton
public class PopupMenuListener implements ActionListener {

	@Inject
	private Controller controller;

	@Override
	public void actionPerformed(ActionEvent e) {
		if ("Save file".equals(e.getActionCommand())) {
			controller.controlSaveWadFile();
		}
	}
}
