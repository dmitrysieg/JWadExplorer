package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.view.listeners.Listeners;
import ru.doom.wad.view.widget.ImagePanel;
import ru.doom.wad.view.widget.PalettePanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

@Singleton
public class ViewManager {

	@Inject
	private Listeners listeners;
	@Inject
	private View view;

	public JFrame createMainFrame() {
		final JFrame mainFrame = new JFrame();
		mainFrame.setTitle("WAD Explorer v.1.0");
		mainFrame.setSize(860, 530);
		mainFrame.setExtendedState(Frame.MAXIMIZED_BOTH);

		mainFrame.setJMenuBar(createMainMenu());

		mainFrame.setLayout(new BorderLayout());

		/* NORTH */

		final JToolBar toolBar = createToolBar();
		mainFrame.getContentPane().add(toolBar, BorderLayout.NORTH);

		/* SOUTH */

		final JPanel southPanel = new JPanel();
		southPanel.setLayout(new CardLayout());
		view.setStatusPanel(southPanel);

		final JProgressBar progressBar = createProgressBar();
		view.setProgressBar(progressBar);
		southPanel.add(progressBar, Controller.SOUTH_PROGRESS);
		final JLabel statusLabel = new JLabel();
		view.setStatusLabel(statusLabel);
		southPanel.add(statusLabel, Controller.SOUTH_STATUS);

		mainFrame.getContentPane().add(southPanel, BorderLayout.SOUTH);

		/* WEST */

		final JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

		final JTextField quickSearch = new JTextField();
		quickSearch.setMaximumSize(new Dimension(Integer.MAX_VALUE, 16));
		quickSearch.addKeyListener(listeners.getQuickSearchActionListener());
		view.setQuickSearch(quickSearch);
		westPanel.add(quickSearch);

		view.setList(createList());
		final JScrollPane listPane = new JScrollPane(view.getList());
		listPane.setPreferredSize(new Dimension(200, 0));
		listPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		view.setListPane(listPane);
		westPanel.add(listPane);

		mainFrame.getContentPane().add(westPanel, BorderLayout.WEST);

		/* EAST */

		final JPanel eastPanel = new JPanel();
		eastPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

		final PalettePanel palettePanel = new PalettePanel();
		palettePanel.init();
		palettePanel.setPreferredSize(new Dimension(200, 0));
		view.setPalettePanel(palettePanel);
		eastPanel.add(palettePanel);

		mainFrame.getContentPane().add(eastPanel, BorderLayout.EAST);

		/* CENTER */

		final JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new CardLayout(10, 10));

		final ImagePanel imagePanel = new ImagePanel();
		view.setImagePanel(imagePanel);

		centerPanel.add(imagePanel, "PREVIEW");

		mainFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);

		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		return mainFrame;
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

	public JList createList() {
		final JList list = new JList();
		list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

		final JPopupMenu wadListMenu = createWadListMenu();
		wadListMenu.setInvoker(list);
		view.setWadListMenu(wadListMenu);
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

	private JToolBar createToolBar() {
		final JToolBar toolBar = new JToolBar(SwingConstants.HORIZONTAL);

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
		return toolBar;
	}
}
