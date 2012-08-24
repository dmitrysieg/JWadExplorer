package ru.doom.wad.view;

import com.google.inject.Singleton;

import javax.swing.*;
import java.awt.*;
import java.io.File;

@Singleton
public class DialogManager {

	public File selectOpenFile(Component parent) {
		final JFileChooser chooser = new JFileChooser();
		final int returnVal = chooser.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}
	
	public File selectSaveWadFile(Component parent, String name) {
		final JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(name));
		final int returnVal = chooser.showSaveDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}
}
