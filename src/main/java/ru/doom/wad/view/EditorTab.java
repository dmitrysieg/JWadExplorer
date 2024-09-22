package ru.doom.wad.view;

import ru.doom.wad.logic.format.wad.Wad;

import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;

public class EditorTab {

    private Wad currentWad;
    ColorModel palette;
    private int currentEntry;
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

    public int getCurrentEntry() {
        return currentEntry;
    }

    public void setCurrentEntry(int currentEntry) {
        this.currentEntry = currentEntry;
    }

    public RenderedImage getCurrentImage() {
        return currentImage;
    }

    public void setCurrentImage(RenderedImage currentImage) {
        this.currentImage = currentImage;
    }
}
