package ru.doom.wad.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class ViewManager {

	public static void createMainFrame() {
		JFrame mainFrame = new JFrame();
		mainFrame.setTitle("WAD Explorer v.1.0");
		mainFrame.setSize(400, 300);
		mainFrame.setJMenuBar(createMainMenu());
		mainFrame.setVisible(true);
	}

	public static JMenuBar createMainMenu() {
		JMenuBar menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic(KeyEvent.VK_F);

		JMenuItem openItem = new JMenuItem("Open", KeyEvent.VK_O);
		openItem.addActionListener(new MainMenuActionListener());
		fileMenu.add(openItem);
		JMenuItem saveItem = new JMenuItem("Save", KeyEvent.VK_S);
		fileMenu.add(saveItem);

		menuBar.add(fileMenu);

		JMenu helpMenu = new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		menuBar.add(helpMenu);

		return menuBar;
	}
}
