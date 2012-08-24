package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;

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
