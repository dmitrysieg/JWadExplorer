package ru.doom.wad.view;

import com.google.inject.Singleton;
import ru.doom.wad.view.widget.ImagePanel;
import ru.doom.wad.view.widget.PalettePanel;

import javax.swing.*;

@Singleton
public class View {

	private JFrame frame;
	private JProgressBar progressBar;
	private JLabel statusLabel;
	private JPanel statusPanel;
	private JList list;
	private JComponent listPane;
	private JTextField quickSearch;
	private JPopupMenu wadListMenu;
	private PalettePanel palettePanel;
	private ImagePanel imagePanel;
	private JButton saveImageButton;

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JLabel getStatusLabel() {
		return statusLabel;
	}

	public void setStatusLabel(JLabel statusLabel) {
		this.statusLabel = statusLabel;
	}

	public JPanel getStatusPanel() {
		return statusPanel;
	}

	public void setStatusPanel(JPanel statusPanel) {
		this.statusPanel = statusPanel;
	}

	public JList getList() {
		return list;
	}

	public void setList(JList list) {
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

	public PalettePanel getPalettePanel() {
		return palettePanel;
	}

	public void setPalettePanel(PalettePanel palettePanel) {
		this.palettePanel = palettePanel;
	}

	public ImagePanel getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}

	public JButton getSaveImageButton() {
		return saveImageButton;
	}

	public void setSaveImageButton(JButton saveImageButton) {
		this.saveImageButton = saveImageButton;
	}
}
