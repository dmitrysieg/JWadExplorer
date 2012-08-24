package ru.doom.wad.view;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class DialogManager {

	public static File selectOpenFile(Component parent) {
		final JFileChooser chooser = new JFileChooser();
		final int returnVal = chooser.showOpenDialog(parent);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}
}
