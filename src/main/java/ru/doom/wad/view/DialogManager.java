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
}
