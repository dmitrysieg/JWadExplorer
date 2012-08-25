package ru.doom.wad.view.listeners;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.view.Controller;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Singleton
public class QuickSearchActionListener implements KeyListener {

	@Inject
	private Controller controller;
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() instanceof JTextField) {
			controller.processQuickSearch();
		}
	}
}
