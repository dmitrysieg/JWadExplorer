package ru.rlane.image.resize;

import java.awt.*;

public final class Geometry {

	/**
	 * Make drawing image preserve its ratio and resize it according to clip constraints
	 */
	public static Rectangle getResizedImageRect(
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
}
