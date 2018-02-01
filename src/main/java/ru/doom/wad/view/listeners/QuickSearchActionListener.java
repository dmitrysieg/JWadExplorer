package ru.doom.wad.view.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.view.Controller;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@Component
public class QuickSearchActionListener implements KeyListener {

	@Autowired
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
