package ru.doom.wad.view;

import ru.doom.wad.logic.format.wad.Wad;

import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;

public class EditorTab {

    private Wad currentWad;
    ColorModel palette;
    private RenderedImage currentImage;

    public Wad getCurrentWad() {
        return currentWad;
    }

    public void setCurrentWad(Wad currentWad) {
        this.currentWad = currentWad;
    }

    public ColorModel getPalette() {
        return palette;
    }

    public void setPalette(ColorModel palette) {
        this.palette = palette;
    }

    public RenderedImage getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(RenderedImage currentImage) {
        this.currentImage = currentImage;
    }
}
