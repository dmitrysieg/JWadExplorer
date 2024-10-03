package ru.doom.wad.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.view.dialog.FileFilters;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.io.File;
import java.util.List;

@Component
public class DialogManager {

	@Autowired private Controller controller;
	@Autowired private View view;
	
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

	public void showPaletteLoaderDialog(List<EditorTab> tabList) {
		SwingUtilities.invokeLater(() -> {
			final JDialog dialog = new JDialog(view.getFrame(), "Choose palette from...");
			dialog.setMinimumSize(new Dimension(200, 100));

			dialog.setLayout(new GridBagLayout());
			GridBagConstraints c = new GridBagConstraints();
			c.insets = new Insets(16,16,16,16);
//			c.fill = GridBagConstraints.BOTH;

			final JList<String> filenameList = new JList<>();
			final DefaultListModel<String> listModel = new DefaultListModel<>();
			tabList.forEach(t -> listModel.addElement(t.getFilename()));
			filenameList.setModel(listModel);

			final JScrollPane scrollPane = new JScrollPane(filenameList);
			scrollPane.setMinimumSize(new Dimension(100, 100));

			c.gridx = 0;
			c.gridy = 0;
			c.gridheight = 2;
			dialog.getContentPane().add(scrollPane, c);

			final JButton okBtn = new JButton("OK");
			okBtn.setEnabled(false);
			filenameList.addListSelectionListener(e -> {
				okBtn.setEnabled(true);
			});
			okBtn.addActionListener(e -> {
				controller.clonePalette(tabList.get(filenameList.getSelectedIndex()));
				dialog.dispose();
			});
			c.gridx = 1;
			c.gridy = 0;
			c.gridheight = 1;
			c.weighty = 0.5;
			dialog.getContentPane().add(okBtn, c);

			final JButton cancelBtn = new JButton("Cancel");
			cancelBtn.addActionListener(e -> {
				dialog.dispose();
			});
			c.gridx = 1;
			c.gridy = 1;
			c.gridheight = 1;
			c.weighty = 0.5;
			dialog.getContentPane().add(cancelBtn, c);

			dialog.pack();

			dialog.setLocationRelativeTo(view.getFrame());
			dialog.setVisible(true);
		});
	}
}
