package ru.doom.wad.view.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.view.Controller;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Component
public class WadListMouseListener implements MouseListener {

	@Autowired
	private Controller controller;

	private void adjustSelection(MouseEvent e) {
		// adjust list selection on right button
		// because of ignoring right button by standard behavior
		if (e.getButton() == MouseEvent.BUTTON3) {
			controller.adjustListSelection(e);
		}
	}

	private void reactOnSelection(MouseEvent e) {
		// Always react on click
		if (e.getButton() == MouseEvent.BUTTON1 || e.getButton() == MouseEvent.BUTTON3) {
			controller.showCurrentResource();
		}

		// Need to show menu
		if (e.getButton() == MouseEvent.BUTTON3) {
			controller.controlWadListMenu(e.getComponent(), e.getX(), e.getY());
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
		adjustSelection(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		reactOnSelection(e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}
