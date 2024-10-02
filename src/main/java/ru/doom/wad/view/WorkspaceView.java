package ru.doom.wad.view;

import ru.doom.wad.view.widget.ImagePanel;
import ru.doom.wad.view.widget.PaletteLoaderFactory;
import ru.doom.wad.view.widget.PalettePanel;

import javax.swing.*;
import java.awt.*;

public class WorkspaceView {

    private JPanel panel;

    private JProgressBar progressBar;
    private JPanel statusPanel;
    private JLabel statusLabel;

    private JList<String> list;
    private JComponent listPane;
    private JTextField quickSearch;

    private JPopupMenu wadListMenu;
    private JToolBar paletteToolbar;
    private PalettePanel palettePanel;
    private PaletteLoaderFactory.PaletteLoader paletteLoader;
    private ImagePanel imagePanel;

    public WorkspaceView(JPanel panel) {
        this.setPanel(panel);
        this.getPanel().setLayout(new BorderLayout());
    }

    public JPanel getPanel() {
        return panel;
    }

    public void setPanel(JPanel panel) {
        this.panel = panel;
    }

    /* */

    public JProgressBar getProgressBar() {
        return progressBar;
    }

    public void setProgressBar(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    public JPanel getStatusPanel() {
        return statusPanel;
    }

    public void setStatusPanel(JPanel statusPanel) {
        this.statusPanel = statusPanel;
    }

    public JLabel getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(JLabel statusLabel) {
        this.statusLabel = statusLabel;
    }

    public JList<String> getList() {
        return list;
    }

    public void setList(JList<String> list) {
        this.list = list;
    }

    public JComponent getListPane() {
        return listPane;
    }

    public void setListPane(JComponent listPane) {
        this.listPane = listPane;
    }

    public JTextField getQuickSearch() {
        return quickSearch;
    }

    public void setQuickSearch(JTextField quickSearch) {
        this.quickSearch = quickSearch;
    }

    public JPopupMenu getWadListMenu() {
        return wadListMenu;
    }

    public void setWadListMenu(JPopupMenu wadListMenu) {
        this.wadListMenu = wadListMenu;
    }

    public JToolBar getPaletteToolbar() {
        return paletteToolbar;
    }

    public void setPaletteToolbar(JToolBar paletteToolbar) {
        this.paletteToolbar = paletteToolbar;
    }

    public PalettePanel getPalettePanel() {
        return palettePanel;
    }

    public void setPalettePanel(PalettePanel palettePanel) {
        this.palettePanel = palettePanel;
    }

    public PaletteLoaderFactory.PaletteLoader getPaletteLoader() {
        return paletteLoader;
    }

    public void setPaletteLoader(PaletteLoaderFactory.PaletteLoader paletteLoader) {
        this.paletteLoader = paletteLoader;
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public void setImagePanel(ImagePanel imagePanel) {
        this.imagePanel = imagePanel;
    }
}
