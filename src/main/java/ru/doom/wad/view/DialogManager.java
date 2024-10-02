package ru.doom.wad.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.view.dialog.FileFilters;

import javax.swing.*;
import java.io.File;

@Component
public class DialogManager {

	@Autowired
	private View view;
	
	public File selectOpenFile() {
		final JFileChooser chooser = new JFileChooser();
		chooser.setFileFilter(FileFilters.WAD_FILE_FILTER);
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
		chooser.setFileFilter(FileFilters.GIF_FILE_FILTER);
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

	public void showPaletteLoaderDialog() {
		final JDialog dialog = new JDialog(view.getFrame(), "Choose palette from...");
		dialog.add(new JLabel("asdasda"));
		dialog.setLocationRelativeTo(view.getFrame());
		dialog.setVisible(true);
	}
}
