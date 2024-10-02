package ru.doom.wad.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.logic.FileController;
import ru.doom.wad.logic.format.wad.Wad;
import ru.doom.wad.logic.format.wad.WadCellRenderer;
import ru.doom.wad.logic.format.wad.WadListModelFactory;
import ru.doom.wad.logic.graphics.DoomGraphicsConverter;
import ru.doom.wad.logic.graphics.GraphicsParsingException;
import ru.doom.wad.view.widget.ImagePanel;

import javax.swing.JList;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Component
public class Controller {

	public static final String SOUTH_PROGRESS = "PROGRESS";
	public static final String SOUTH_STATUS = "STATUS";

	private boolean isAllowedSaveFile;
	private boolean isAllowedSaveImage;

	@Autowired
	private View view;
	@Autowired
	private ViewManager viewManager;
	@Autowired
	private DialogManager dialogManager;
	@Autowired
	private WadListModelFactory wadListModelFactory;
	@Autowired
	private FileController fileController;
	@Autowired
	private DoomGraphicsConverter doomGraphicsConverter;

	private final Map<String, EditorTab> tabs = new HashMap<>();
	private EditorTab currentTab;
	private boolean tabBeingCreated;

	public void addEditorTab(final String absolutePath, final String filename) {
		tabBeingCreated = true;

		final WorkspaceView workspaceView = viewManager.addWorkspace(view.getTabbedPane(), filename);
		view.setCurrentWorkspace(workspaceView);

		currentTab = new EditorTab(workspaceView, absolutePath);
		tabs.put(absolutePath, currentTab);

		tabBeingCreated = false;
	}

	public void changeTab(final int index) {
		final JPanel panel = (JPanel) view.getTabbedPane().getComponentAt(index);
		final EditorTab tab = findEditorTab(panel);

		view.setCurrentWorkspace(tab.getWorkspaceView());
		currentTab = tab;
	}

	public EditorTab getCurrentTab() {
		return currentTab;
	}

	public EditorTab findEditorTab(final JPanel tabPanel) {
		return tabs.values()
				.stream()
				.filter(editorTab -> editorTab.getWorkspaceView().getPanel().equals(tabPanel))
				.findFirst()
				.orElseThrow(() -> new IllegalStateException("No tab corresponding to panel"));
	}

	public void removeEditorTab(final String absolutePath) {
		viewManager.removeWorkspace(tabs.get(absolutePath));
		tabs.remove(absolutePath);
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

		if (palette != null) {
			viewManager.createPalettePanel(view.getCurrentWorkspace());
			view.getCurrentWorkspace().getPalettePanel().setPalette(palette);
		} else {
			viewManager.createPaletteLoader(view.getCurrentWorkspace());
			adjustAnyPaletteExist();
		}

		view.getCurrentWorkspace().getPanel().revalidate();
		view.getCurrentWorkspace().getPanel().repaint();

		view.getCurrentWorkspace().getList().setCellRenderer(new WadCellRenderer().wad(this.currentTab.getCurrentWad()));
		view.getCurrentWorkspace().getList().setModel(wadListModelFactory.withWad(this.currentTab.getCurrentWad()));
		view.getCurrentWorkspace().getListPane().doLayout();
	}

	public void adjustAnyPaletteExist() {
		final boolean showButton = tabs.values()
				.stream()
				.anyMatch(tab -> tab.palette != null);
		if (view.getCurrentWorkspace().getPaletteLoader() != null) {
			view.getCurrentWorkspace().getPaletteLoader().getButton().setEnabled(showButton);
		}
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

	public void adjustListSelection(MouseEvent e) {
		Object ui = view.getCurrentWorkspace().getList().getUI();
		try {
			Method getHandler = ui.getClass().getDeclaredMethod("getHandler");
			getHandler.setAccessible(true);
			Object handler = getHandler.invoke(ui);

			Method adjustSelection = handler.getClass().getDeclaredMethod("adjustSelection", MouseEvent.class);
			adjustSelection.setAccessible(true);
			adjustSelection.invoke(handler, e);
		} catch (NoSuchMethodException noSuchMethodException) {
			// ignore
		} catch (InvocationTargetException invocationTargetException) {
			// ignore
		} catch (IllegalAccessException illegalAccessException) {
			// ignore
		}
	}

	public void showCurrentResource() {

		final JList<String> list = view.getCurrentWorkspace().getList();

		if (list.getSelectedIndex() >= 0) {

			allowSaveFile(true);
			currentTab.setCurrentEntry(list.getSelectedIndex());

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

	public void allowSaveFile(boolean allowed) {
		isAllowedSaveFile = allowed;
		view.getSaveFileButton().setEnabled(allowed);
	}

	public void allowSaveImage(boolean allowed) {
		isAllowedSaveImage = allowed;
		view.getSaveImageButton().setEnabled(allowed);
	}

	public boolean isAllowedSaveFile() {
		return isAllowedSaveFile;
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

	public boolean isTabBeingCreated() {
		return tabBeingCreated;
	}
}
