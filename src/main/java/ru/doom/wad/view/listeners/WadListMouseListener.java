package ru.doom.wad.view.listeners;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.view.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Singleton
public class WadListMouseListener implements MouseListener {

	@Inject
	private Controller controller;

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			controller.controlWadListMenu(e.getComponent(), e.getX(), e.getY());
		} else if (e.getButton() == MouseEvent.BUTTON1 && e.getClickCount() == 2) {
			controller.showCurrentResource();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}