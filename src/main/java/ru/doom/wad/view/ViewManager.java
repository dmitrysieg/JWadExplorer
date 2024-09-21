package ru.doom.wad.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.logic.app.VersionHelper;
import ru.doom.wad.view.listeners.Listeners;
import ru.doom.wad.view.widget.ImagePanel;
import ru.doom.wad.view.widget.PalettePanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

@Component
public class ViewManager {

	@Autowired private Listeners listeners;
	@Autowired private View view;
	@Autowired private VersionHelper versionHelper;

	public JFrame initMainFrame() {
		final JFrame mainFrame = createMainFrame();

		mainFrame.add(createToolBar());

		final JTabbedPane tabbedPane = createTabbedPane();
		mainFrame.add(tabbedPane);

		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);

		view.setFrame(mainFrame);

		return mainFrame;
	}

	public JFrame createMainFrame() {

		final JFrame mainFrame = new JFrame();
		mainFrame.setTitle(String.format("WAD Explorer %s", versionHelper.getVersion()));
		mainFrame.setSize(860, 530);
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

		mainFrame.setJMenuBar(createMainMenu());

		mainFrame.setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.PAGE_AXIS));
		return mainFrame;
	}

	public JTabbedPane createTabbedPane() {

		final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		view.setTabbedPane(tabbedPane);
		return tabbedPane;
	}

	public WorkspaceView addWorkspace(final JTabbedPane tabbedPane, final String filename) {

		final WorkspaceView workspace = new WorkspaceView(new JPanel());

		workspace.getPanel().add(createBottomPanel(workspace), BorderLayout.SOUTH);
		workspace.getPanel().add(createLeftPanel(workspace), BorderLayout.WEST);
		workspace.getPanel().add(createRightPanel(workspace), BorderLayout.EAST);
		workspace.getPanel().add(createCenterPanel(workspace), BorderLayout.CENTER);

		view.getTabbedPane().addTab(filename, workspace.getPanel());
		return workspace;
	}

	public JPanel createBottomPanel(final WorkspaceView workspace) {

		final JPanel southPanel = new JPanel();
		southPanel.setLayout(new CardLayout());
		workspace.setStatusPanel(southPanel);

		final JProgressBar progressBar = createProgressBar();
		workspace.setProgressBar(progressBar);
		southPanel.add(progressBar, Controller.SOUTH_PROGRESS);

		final JLabel statusLabel = new JLabel();
		workspace.setStatusLabel(statusLabel);
		southPanel.add(statusLabel, Controller.SOUTH_STATUS);

		return southPanel;
	}

	public JPanel createLeftPanel(final WorkspaceView workspace) {

		final JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

		final JTextField quickSearch = new JTextField();
		quickSearch.setMaximumSize(new Dimension(Integer.MAX_VALUE, 16));
		quickSearch.addKeyListener(listeners.getQuickSearchActionListener());
		workspace.setQuickSearch(quickSearch);
		westPanel.add(quickSearch);

		workspace.setList(createList(workspace));
		final JScrollPane listPane = new JScrollPane(workspace.getList());
		listPane.setPreferredSize(new Dimension(200, 0));
		listPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		workspace.setListPane(listPane);
		westPanel.add(listPane);

		return westPanel;
	}

	public JPanel createRightPanel(final WorkspaceView workspace) {

		final JPanel eastPanel = new JPanel();
		eastPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

		final PalettePanel palettePanel = new PalettePanel();
		palettePanel.init();
		palettePanel.setPreferredSize(new Dimension(200, 0));
		workspace.setPalettePanel(palettePanel);

		final JToolBar paletteToolbar = new JToolBar(SwingConstants.VERTICAL);
		paletteToolbar.setPreferredSize(new Dimension(200, 200));
		paletteToolbar.add(palettePanel);
		eastPanel.add(paletteToolbar);

		return eastPanel;
	}

	public JPanel createCenterPanel(final WorkspaceView workspace) {

		final JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new CardLayout(10, 10));

		final ImagePanel imagePanel = new ImagePanel();
		workspace.setImagePanel(imagePanel);

		centerPanel.add(imagePanel, "PREVIEW");
		return centerPanel;
	}

	public JMenuBar createMainMenu() {
		final JMenuBar menuBar = new JMenuBar();

		final JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		final JMenuItem openItem = new JMenuItem("Open", KeyEvent.VK_O);
		openItem.addActionListener(listeners.getMainMenuActionListener());
		fileMenu.add(openItem);
		final JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_S);
		fileMenu.add(saveItem);

		menuBar.add(fileMenu);

		final JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpMenu);

		return menuBar;
	}

	public JProgressBar createProgressBar() {
		final JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL);
		progressBar.setPreferredSize(new Dimension(0, 16));
		return progressBar;
	}

	public JList<String> createList(final WorkspaceView workspace) {
		final JList<String> list = new JList<>();
		list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

		final JPopupMenu wadListMenu = createWadListMenu();
		wadListMenu.setInvoker(list);
		workspace.setWadListMenu(wadListMenu);
		list.addMouseListener(listeners.getWadListMouseListener());

		return list;
	}

	public JPopupMenu createWadListMenu() {
		final JPopupMenu wadListMenu = new JPopupMenu();

		final JMenuItem saveItem = new JMenuItem(Commands.SAVE_FILE);
		saveItem.addActionListener(listeners.getPopupMenuListener());
		wadListMenu.add(saveItem);

		return wadListMenu;
	}

	private JPanel createToolBar() {
		final JToolBar toolBar = new JToolBar(SwingConstants.HORIZONTAL);
		toolBar.setFloatable(false);
		toolBar.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

		final URL urlSave = this.getClass().getResource("icon-save.png");
		final Icon iconSave = new ImageIcon(urlSave, "Save image");
		final JButton btnSave = new JButton(iconSave);
		btnSave.setBorder(BorderFactory.createEmptyBorder());
		btnSave.setActionCommand("Save image");
		btnSave.addActionListener(listeners.getToolbarActionListener());
		btnSave.setToolTipText("Save image");
		btnSave.setEnabled(false);
		view.setSaveImageButton(btnSave);

		toolBar.add(btnSave);

		final JPanel toolBarFrame = new JPanel();
		toolBarFrame.setLayout(new BoxLayout(toolBarFrame, BoxLayout.LINE_AXIS));
		toolBar.setMaximumSize(new Dimension(Integer.MAX_VALUE, toolBar.getPreferredSize().height));
		toolBarFrame.add(toolBar);
		return toolBarFrame;
	}
}
