package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.view.widget.ImagePanel;
import ru.doom.wad.view.widget.PalettePanel;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import java.awt.*;
import java.awt.event.KeyEvent;

@Singleton
public class ViewManager {

	@Inject
	private Listeners listeners;
	@Inject
	private Controller controller;

	public JFrame createMainFrame() {
		final JFrame mainFrame = new JFrame();
		mainFrame.setTitle("WAD Explorer v.1.0");
		mainFrame.setSize(400, 300);

		mainFrame.setJMenuBar(createMainMenu());

		mainFrame.setLayout(new BorderLayout());

		/* SOUTH */

		controller.setProgressBar(createProgressBar());
		mainFrame.getContentPane().add(controller.getProgressBar(), BorderLayout.SOUTH);

		/* WEST */

		final JPanel westPanel = new JPanel();
		westPanel.setLayout(new BoxLayout(westPanel, BoxLayout.Y_AXIS));

		final JTextField quickSearch = new JTextField();
		quickSearch.setMaximumSize(new Dimension(Integer.MAX_VALUE, 16));
		quickSearch.addKeyListener(listeners.getQuickSearchActionListener());
		controller.setQuickSearch(quickSearch);
		westPanel.add(quickSearch);

		controller.setList(createList());
		final JScrollPane listPane = new JScrollPane(controller.getList());
		listPane.setPreferredSize(new Dimension(200, 0));
		listPane.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		controller.setListPane(listPane);
		westPanel.add(listPane);

		mainFrame.getContentPane().add(westPanel, BorderLayout.WEST);

		/* EAST */

		final JPanel eastPanel = new JPanel();
		eastPanel.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
		eastPanel.setLayout(new BoxLayout(eastPanel, BoxLayout.Y_AXIS));

		final PalettePanel palettePanel = new PalettePanel();
		palettePanel.setPreferredSize(new Dimension(200, 0));
		controller.setPalettePanel(palettePanel);
		eastPanel.add(palettePanel);

		mainFrame.getContentPane().add(eastPanel, BorderLayout.EAST);

		/* CENTER */

		final JPanel centerPanel = new JPanel();
		centerPanel.setLayout(new GridBagLayout());

		final ImagePanel imagePanel = new ImagePanel();
		imagePanel.setPreferredSize(new Dimension(200, 200));
		controller.setImagePanel(imagePanel);
		
		final GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.CENTER;
		c.gridx = 1;
		c.gridy = 1;
		centerPanel.add(imagePanel, c);

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
		controller.setWadListMenu(wadListMenu);
		list.addMouseListener(listeners.getWadListMouseListener());

		return list;
	}

	public JPopupMenu createWadListMenu() {
		final JPopupMenu wadListMenu = new JPopupMenu();

		final JMenuItem saveItem = new JMenuItem("Save file");
		saveItem.addActionListener(listeners.getPopupMenuListener());
		wadListMenu.add(saveItem);

		return wadListMenu;
	}
}
