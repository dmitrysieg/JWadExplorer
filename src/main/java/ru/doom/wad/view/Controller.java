package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.logic.FileController;
import ru.doom.wad.logic.Wad;
import ru.doom.wad.logic.graphics.DoomGraphicsConverter;
import ru.doom.wad.logic.graphics.GraphicsParsingException;
import ru.doom.wad.view.widget.ImagePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ColorModel;
import java.awt.image.RenderedImage;
import java.io.IOException;

@Singleton
public class Controller {

	public static final String SOUTH_PROGRESS = "PROGRESS";
	public static final String SOUTH_STATUS = "STATUS";

	private boolean isAllowedSaveImage;
	private Wad currentWad;
	private RenderedImage currentImage;

	@Inject
	private View view;
	@Inject
	private DialogManager dialogManager;
	@Inject
	private WadListModel wadListModel;
	@Inject
	private FileController fileController;
	@Inject
	private DoomGraphicsConverter doomGraphicsConverter;

	@Inject
	public Controller() {
		isAllowedSaveImage = false;
		currentWad = null;
		currentImage = null;
	}

	public void processQuickSearch() {
		String prefix = view.getQuickSearch().getText();
		final JList list = view.getList();
		for (int i = 0; i < list.getModel().getSize(); i++) {
			if (((String)list.getModel().getElementAt(i)).startsWith(prefix)) {
				list.setSelectedIndex(i);
				list.ensureIndexIsVisible(i);
				break;
			}
		}
	}

	public void processOnLoadWad(ColorModel palette) {
		view.setPalette(palette);
		view.getPalettePanel().setPalette(palette);
		view.getPalettePanel().repaint();
		view.getList().setCellRenderer(new WadCellRenderer().wad(currentWad));
		view.getList().setModel(wadListModel.withWad(currentWad));
		view.getListPane().doLayout();
	}

	public void controlWadListMenu(Component invoker, int x, int y) {
		if (view.getList().getSelectedIndex() >= 0) {
			view.getWadListMenu().show(invoker, x, y);
		}
	}

	public void controlSaveWadFile() {
		final JList list = view.getList();
		try {
			fileController.saveWadFile(
					dialogManager.selectSaveWadFile(list.getSelectedValue().toString()),
					currentWad.get(list.getSelectedIndex()).getContent()
			);
		} catch (IOException e) {
			dialogManager.showErrorMessageDialog("Error saving file", e.getLocalizedMessage());
		}
	}

	public void showCurrentResource() {
		final JList list = view.getList();
		if (list.getSelectedIndex() >= 0) {
			final ColorModel palette = view.getPalette();
			if (palette != null) {
				final byte[] imageFile = currentWad.get(list.getSelectedIndex()).getContent();
				try {
					final ImagePanel imagePanel = view.getImagePanel();
					currentImage = doomGraphicsConverter.convertSprite(imageFile, view.getPalette());
					imagePanel.setImage((Image)currentImage);
					imagePanel.repaint();
					showStatus("");
					allowSaveImage(true);
				} catch (GraphicsParsingException e) {
					currentImage = null;
					showStatus("Not a graphics file");
					allowSaveImage(false);
				}
			}
		}
	}

	private void showStatus(String status) {
		view.getStatusLabel().setText(status);
		final CardLayout cardLayout = (CardLayout)view.getStatusPanel().getLayout();
		cardLayout.show(view.getStatusPanel(), SOUTH_STATUS);
	}

	public void showProgress() {
		final CardLayout cardLayout = (CardLayout)view.getStatusPanel().getLayout();
		cardLayout.show(view.getStatusPanel(), SOUTH_PROGRESS);
	}

	public void showError(String header, String message) {
		dialogManager.showErrorMessageDialog(header, message);
	}

	public void allowSaveImage(boolean allowed) {
		isAllowedSaveImage = allowed;
		view.getSaveImageButton().setEnabled(allowed);
	}

	public boolean isAllowedSaveImage() {
		return isAllowedSaveImage;
	}

	public void setCurrentWad(Wad currentWad) {
		this.currentWad = currentWad;
	}

	public void saveCurrentImage() {
		try {
			fileController.saveImageFile(
					dialogManager.selectSaveImageFile(view.getList().getSelectedValue().toString()),
					currentImage
			);
		} catch (Exception e) {
			dialogManager.showErrorMessageDialog("Error saving file", e.getLocalizedMessage());
		}
	}
}
