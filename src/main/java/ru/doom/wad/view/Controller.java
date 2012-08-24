package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.logic.FileController;
import ru.doom.wad.logic.graphics.DoomGraphicsConverter;
import ru.doom.wad.view.widget.ImagePanel;
import ru.doom.wad.view.widget.Palette;
import ru.doom.wad.view.widget.PalettePanel;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@Singleton
public class Controller {

	@Inject
	private DialogManager dialogManager;
	@Inject
	private FileController fileController;
	@Inject
	private DoomGraphicsConverter doomGraphicsConverter;

	private int openedFilesCount;
	private JFrame frame;
	private JProgressBar progressBar;
	private JList list;
	private JComponent listPane;
	private JTextField quickSearch;
	private JPopupMenu wadListMenu;
	private PalettePanel palettePanel;
	private ImagePanel imagePanel;

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JList getList() {
		return list;
	}

	public void setList(JList list) {
		this.list = list;
	}

	public void handleOpenedWad() {
		if (openedFilesCount == 0) {
		}
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

	public void processQuickSearch() {
		String prefix = quickSearch.getText();
		for (int i = 0; i < list.getModel().getSize(); i++) {
			if (((String)list.getModel().getElementAt(i)).startsWith(prefix)) {
				list.setSelectedIndex(i);
				list.ensureIndexIsVisible(i);
				break;
			}
		}
	}

	public void controlWadListMenu(Component invoker, int x, int y) {
		if (list.getSelectedIndex() >= 0) {
			wadListMenu.show(invoker, x, y);
		}
	}

	public JPopupMenu getWadListMenu() {
		return wadListMenu;
	}

	public void setWadListMenu(JPopupMenu wadListMenu) {
		this.wadListMenu = wadListMenu;
	}

	public void controlSaveWadFile() {
		try {
			fileController.saveWadFile(
					dialogManager.selectSaveWadFile(frame, list.getSelectedValue().toString()),
					((WadListModel)list.getModel()).getWad().get(list.getSelectedIndex()).getContent()
			);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(
					frame,
					e.getLocalizedMessage(),
					"Error saving file",
					JOptionPane.ERROR_MESSAGE
			);
		}
	}

	public PalettePanel getPalettePanel() {
		return palettePanel;
	}

	public void setPalettePanel(PalettePanel palettePanel) {
		this.palettePanel = palettePanel;
	}

	public void showCurrentResource() {
		if (list.getSelectedIndex() >= 0) {
			final Palette palette = palettePanel.getPalette();
			if (palette != null) {
				final byte[] imageFile = ((WadListModel)list.getModel()).getWad().get(list.getSelectedIndex()).getContent();
				imagePanel.setImage(doomGraphicsConverter.convertSprite(imageFile, palette));
				imagePanel.repaint();
			}
		}
	}

	public ImagePanel getImagePanel() {
		return imagePanel;
	}

	public void setImagePanel(ImagePanel imagePanel) {
		this.imagePanel = imagePanel;
	}
}
