package ru.doom.wad.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.logic.FileController;
import ru.doom.wad.logic.format.wad.Wad;
import ru.doom.wad.logic.format.wad.WadCellRenderer;
import ru.doom.wad.logic.format.wad.WadListModel;
import ru.doom.wad.logic.graphics.DoomGraphicsConverter;
import ru.doom.wad.logic.graphics.GraphicsParsingException;
import ru.doom.wad.view.widget.ImagePanel;

import javax.swing.*;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class Controller {

	public static final String SOUTH_PROGRESS = "PROGRESS";
	public static final String SOUTH_STATUS = "STATUS";

	private boolean isAllowedSaveImage;

	@Autowired
	private View view;
	@Autowired
	private ViewManager viewManager;
	@Autowired
	private DialogManager dialogManager;
	@Autowired
	private WadListModel wadListModel;
	@Autowired
	private FileController fileController;
	@Autowired
	private DoomGraphicsConverter doomGraphicsConverter;

	private Map<String, EditorTab> tabs = new HashMap<>();
	private EditorTab currentTab;

	public void addEditorTab(final String absolutePath, final String filename) {
		view.setCurrentWorkspace(viewManager.addWorkspace(view.getTabbedPane(), filename));

		currentTab = new EditorTab();
		tabs.put(absolutePath, currentTab);
	}

	public void processQuickSearch() {
		String prefix = view.getCurrentWorkspace().getQuickSearch().getText();
		final JList<String> list = view.getCurrentWorkspace().getList();
		for (int i = 0; i < list.getModel().getSize(); i++) {
			if ((list.getModel().getElementAt(i)).startsWith(prefix)) {
				list.setSelectedIndex(i);
				list.ensureIndexIsVisible(i);
				break;
			}
		}
	}

	public void processOnLoadWad(ColorModel palette) {

		this.currentTab.setPalette(palette);

		view.getCurrentWorkspace().getPalettePanel().setPalette(palette);
		view.getCurrentWorkspace().getPalettePanel().repaint();
		view.getCurrentWorkspace().getList().setCellRenderer(new WadCellRenderer().wad(this.currentTab.getCurrentWad()));
		view.getCurrentWorkspace().getList().setModel(wadListModel.withWad(this.currentTab.getCurrentWad()));
		view.getCurrentWorkspace().getListPane().doLayout();
	}

	public void controlWadListMenu(java.awt.Component invoker, int x, int y) {
		if (view.getCurrentWorkspace().getList().getSelectedIndex() >= 0) {
			view.getCurrentWorkspace().getWadListMenu().show(invoker, x, y);
		}
	}

	public void controlSaveWadFile() {
		final JList<String> list = view.getCurrentWorkspace().getList();
		try {
			fileController.saveWadFile(
					dialogManager.selectSaveWadFile(list.getSelectedValue()),
					this.currentTab.getCurrentWad().get(list.getSelectedIndex()).getContent()
			);
		} catch (IOException e) {
			dialogManager.showErrorMessageDialog("Error saving file", e.getLocalizedMessage());
		}
	}

	public void showCurrentResource() {
		final JList<String> list = view.getCurrentWorkspace().getList();
		if (list.getSelectedIndex() >= 0) {
			final ColorModel palette = currentTab.getPalette();
			if (palette != null) {
				final byte[] imageFile = this.currentTab.getCurrentWad().get(list.getSelectedIndex()).getContent();
				try {
					final ImagePanel imagePanel = view.getCurrentWorkspace().getImagePanel();
					this.currentTab.setCurrentImage(doomGraphicsConverter.convertSprite(imageFile, currentTab.getPalette()));
					imagePanel.setImage((Image) this.currentTab.getCurrentImage());
					imagePanel.repaint();
					showStatus("");
					allowSaveImage(true);
				} catch (GraphicsParsingException e) {
					this.currentTab.setCurrentImage(null);
					showStatus("Not a graphics file");
					allowSaveImage(false);
				}
			}
		}
	}

	private void showStatus(String status) {
		view.getCurrentWorkspace().getStatusLabel().setText(status);
		final CardLayout cardLayout = (CardLayout) view.getCurrentWorkspace().getStatusPanel().getLayout();
		cardLayout.show(view.getCurrentWorkspace().getStatusPanel(), SOUTH_STATUS);
	}

	public void showProgress() {
		final CardLayout cardLayout = (CardLayout) view.getCurrentWorkspace().getStatusPanel().getLayout();
		cardLayout.show(view.getCurrentWorkspace().getStatusPanel(), SOUTH_PROGRESS);
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
		this.currentTab.setCurrentWad(currentWad);
	}

	public void saveCurrentImage() {
		try {
			fileController.saveImageFile(
					dialogManager.selectSaveImageFile(view.getCurrentWorkspace().getList().getSelectedValue()),
					this.currentTab.getCurrentImage()
			);
		} catch (Exception e) {
			dialogManager.showErrorMessageDialog("Error saving file", e.getLocalizedMessage());
		}
	}
}
