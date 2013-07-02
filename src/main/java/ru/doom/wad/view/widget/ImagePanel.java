package ru.doom.wad.view.widget;

import javax.swing.*;
import java.awt.*;
import java.awt.image.*;

public class ImagePanel extends JPanel {

	private static final int TILE_SIZE = 5;

	private static TexturePaint texture = createTileTexture();
	private Image image;
	
	public void setImage(Image image) {
		this.image = image;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		final int h = g.getClipBounds().height;
		final int w = g.getClipBounds().width;

		if (image != null) {
			final Rectangle rect = getResizedImageRect(w, h, image.getWidth(null), image.getHeight(null));
			g.drawImage(
					image,
					rect.x,
					rect.y,
					rect.width,
					rect.height,
					null
			);
		} else {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setPaint(texture);
			g2d.fill(g2d.getClip());
		}
	}

	/**
	 * Make drawing image preserve its ratio and resize it according to clip constraints
	 */
	private static Rectangle getResizedImageRect(
			int clipWidth,
			int clipHeight,
			int imageWidth,
			int imageHeight
	) {
		final double clipRatio = ((double) clipWidth) / ((double) clipHeight);
		final double imageRatio = ((double) imageWidth) / ((double) imageHeight);
		if (imageRatio < clipRatio) {
			final int newWidth = (int)(clipHeight * imageRatio);
			return new Rectangle(
					(clipWidth - newWidth) / 2,
					0,
					newWidth,
					clipHeight
			);
		} else {
			final int newHeight = (int)(clipWidth / imageRatio);
			return new Rectangle(
					0,
					(clipHeight - newHeight) / 2,
					clipWidth,
					newHeight
			);
		}
	}

	private static TexturePaint createTileTexture() {
		BufferedImage bufferedImage = new BufferedImage(TILE_SIZE * 2, TILE_SIZE * 2, BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = bufferedImage.createGraphics();
		g2.setPaint(Color.LIGHT_GRAY);
		g2.fill(new Rectangle(0, 0, TILE_SIZE, TILE_SIZE));
		g2.fill(new Rectangle(TILE_SIZE, TILE_SIZE, TILE_SIZE, TILE_SIZE));
		g2.setPaint(Color.WHITE);
		g2.fill(new Rectangle(0, TILE_SIZE, TILE_SIZE, TILE_SIZE));
		g2.fill(new Rectangle(TILE_SIZE, 0, TILE_SIZE, TILE_SIZE));
		return new TexturePaint(bufferedImage, new Rectangle(TILE_SIZE * 2, TILE_SIZE * 2));
	}
}
