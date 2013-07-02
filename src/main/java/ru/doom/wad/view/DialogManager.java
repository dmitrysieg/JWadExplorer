package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.regex.Pattern;

@Singleton
public class DialogManager {

	private static final FileFilter GIF_FILE_FILTER = new FileFilter() {
		private final Pattern GIF_REGEXP = Pattern.compile(".*?gif$", Pattern.CASE_INSENSITIVE);
		@Override public boolean accept(File f) {return f.isDirectory() || GIF_REGEXP.matcher(f.getName()).matches();}
		@Override public String getDescription() {return "GIF Files (*.gif)";}
	};

	@Inject
	private View view;
	
	public File selectOpenFile() {
		final JFileChooser chooser = new JFileChooser();
		final int returnVal = chooser.showOpenDialog(view.getFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}
	
	public File selectSaveWadFile(String name) {
		final JFileChooser chooser = new JFileChooser();
		chooser.setSelectedFile(new File(name));
		final int returnVal = chooser.showSaveDialog(view.getFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}

	// TODO: reduce duplicated code
	public File selectSaveImageFile(String name) {
		final JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(GIF_FILE_FILTER);
		chooser.setSelectedFile(new File(name + ".gif"));
		final int returnVal = chooser.showSaveDialog(view.getFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			return chooser.getSelectedFile();
		} else {
			return null;
		}
	}

	public void showErrorMessageDialog(String header, String message) {
		JOptionPane.showMessageDialog(view.getFrame(), message, header, JOptionPane.ERROR_MESSAGE);
	}
}
