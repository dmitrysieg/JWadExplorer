package ru.doom.wad.view;

import ru.doom.wad.logic.format.wad.Wad;

import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;

public class EditorTab {

    private WorkspaceView workspaceView;
    private String absolutePath;

    private Wad currentWad;
    ColorModel palette;
    private int currentEntry;
    private RenderedImage currentImage;

    public EditorTab(WorkspaceView workspaceView, String absolutePath) {
        this.workspaceView = workspaceView;
        this.absolutePath = absolutePath;
    }

    public String getAbsolutePath() {
        return absolutePath;
    }

    public void setAbsolutePath(String absolutePath) {
        this.absolutePath = absolutePath;
    }

    public WorkspaceView getWorkspaceView() {
        return workspaceView;
    }

    public void setWorkspaceView(WorkspaceView workspaceView) {
        this.workspaceView = workspaceView;
    }

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
