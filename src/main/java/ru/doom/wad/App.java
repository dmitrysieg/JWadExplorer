package ru.doom.wad;

import ru.doom.wad.view.ViewManager;

import javax.swing.*;

public class App {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				ViewManager.createMainFrame();
			}
		});
	}
}
