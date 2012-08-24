package ru.doom.wad.view.widget;

import javax.swing.*;
import java.awt.*;

public class ImagePanel extends JPanel {

	private Image image;
	
	public void setImage(Image image) {
		this.image = image;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (image != null) {
			final int h = g.getClipBounds().height;
			final int w = g.getClipBounds().width;
			g.drawImage(image, 0, 0, w, h, null);
		}
	}
}
