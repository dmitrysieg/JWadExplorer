package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.view.widget.PalettePanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

@Singleton
public class ViewManager {

	@Inject
	private MainMenuActionListener mainMenuActionListener;
	@Inject
	private QuickSearchActionListener quickSearchActionListener;
	@Inject
	private WadListMouseListener wadListMouseListener;
	@Inject
	private PopupMenuListener popupMenuListener;
	@Inject
	private Controller controller;

	public JFrame createMainFrame() {
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("WAD Explorer v.1.0");
		mainFrame.setSize(400, 300);

		mainFrame.setJMenuBar(createMainMenu());

		mainFrame.setLayout(new BorderLayout());

		/* SOUTH */

		controller.setProgressBar(createProgressBar());
		mainFrame.getContentPane().add(controller.getProgressBar(), BorderLayout.SOUTH);

		/* WEST */

		JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

		JTextField quickSearch = new JTextField();
		quickSearch.setMaximumSize(new Dimension(Integer.MAX_VALUE, 16));
		quickSearch.addKeyListener(quickSearchActionListener);
		controller.setQuickSearch(quickSearch);
		westPanel.add(quickSearch);

		controller.setList(createList());
		JScrollPane listPane = new JScrollPane(controller.getList());
		listPane.setPreferredSize(new Dimension(200, 0));
		listPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		controller.setListPane(listPane);
		westPanel.add(listPane);

		mainFrame.getContentPane().add(westPanel, BorderLayout.WEST);

		/* EAST */

		JPanel eastPanel = new JPanel();
		eastPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

		PalettePanel palettePanel = new PalettePanel();
		palettePanel.setPreferredSize(new Dimension(200, 0));
		controller.setPalettePanel(palettePanel);
		eastPanel.add(palettePanel);

		mainFrame.getContentPane().add(eastPanel, BorderLayout.EAST);

		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
		return mainFrame;
	}

	public JMenuBar createMainMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem openItem = new JMenuItem("Open", KeyEvent.VK_O);
		openItem.addActionListener(mainMenuActionListener);
		fileMenu.add(openItem);
		JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_S);
		fileMenu.add(saveItem);

		menuBar.add(fileMenu);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpMenu);

		return menuBar;
	}

	public JProgressBar createProgressBar() {
		JProgressBar progressBar = new JProgressBar(JProgressBar.HORIZONTAL);
		progressBar.setPreferredSize(new Dimension(0, 16));
		return progressBar;
	}

	public JList createList() {
		JList list = new JList();
		list.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

		JPopupMenu wadListMenu = createWadListMenu();
		wadListMenu.setInvoker(list);
		controller.setWadListMenu(wadListMenu);
		list.addMouseListener(wadListMouseListener);

		return list;
	}

	public JPopupMenu createWadListMenu() {
		JPopupMenu wadListMenu = new JPopupMenu();

		JMenuItem saveItem = new JMenuItem("Save file");
		saveItem.addActionListener(popupMenuListener);
		wadListMenu.add(saveItem);

		return wadListMenu;
	}
}
