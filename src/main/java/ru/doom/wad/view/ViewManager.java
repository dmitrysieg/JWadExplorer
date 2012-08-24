package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;

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
	private Controller controller;

	public JFrame createMainFrame() {
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("WAD Explorer v.1.0");
		mainFrame.setSize(400, 300);

		mainFrame.setJMenuBar(createMainMenu());

		mainFrame.setLayout(new BorderLayout());

		controller.setProgressBar(createProgressBar());
		mainFrame.getContentPane().add(controller.getProgressBar(), BorderLayout.SOUTH);

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
		return list;
	}
}
