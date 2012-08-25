package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.logic.FileController;
import ru.doom.wad.logic.graphics.DoomGraphicsConverter;
import ru.doom.wad.logic.graphics.GraphicsParsingException;
import ru.doom.wad.view.widget.ImagePanel;
import ru.doom.wad.view.widget.Palette;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

@Singleton
public class Controller {

	public static final String SOUTH_PROGRESS = "PROGRESS";
	public static final String SOUTH_STATUS = "STATUS";

	@Inject
	private View view;
	@Inject
	private DialogManager dialogManager;
	@Inject
	private FileController fileController;
	@Inject
	private DoomGraphicsConverter doomGraphicsConverter;

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
					((WadListModel)list.getModel()).getWad().get(list.getSelectedIndex()).getContent()
			);
		} catch (IOException e) {
			dialogManager.showErrorMessageDialog("Error saving file", e.getLocalizedMessage());
		}
	}

	public void showCurrentResource() {
		final JList list = view.getList();
		if (list.getSelectedIndex() >= 0) {
			final Palette palette = view.getPalettePanel().getPalette();
			if (palette != null) {
				final byte[] imageFile = ((WadListModel)list.getModel()).getWad().get(list.getSelectedIndex()).getContent();
				try {
					final ImagePanel imagePanel = view.getImagePanel();
					imagePanel.setImage(doomGraphicsConverter.convertSprite(imageFile, palette));
					imagePanel.repaint();
					showStatus("");
				} catch (GraphicsParsingException e) {
					showStatus("Not a graphics file");
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
}
