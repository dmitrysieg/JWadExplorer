package ru.doom.wad.view;

import com.google.inject.Singleton;

import javax.swing.*;

@Singleton
public class Controller {

	private int openedFilesCount;
	private JFrame frame;
	private JProgressBar progressBar;
	private JList list;
	private JComponent listPane;
	private JTextField quickSearch;

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JFrame getFrame() {
		return frame;
	}

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public JList getList() {
		return list;
	}

	public void setList(JList list) {
		this.list = list;
	}

	public void handleOpenedWad() {
		if (openedFilesCount == 0) {
		}
	}

	public JComponent getListPane() {
		return listPane;
	}

	public void setListPane(JComponent listPane) {
		this.listPane = listPane;
	}

	public JTextField getQuickSearch() {
		return quickSearch;
	}

	public void setQuickSearch(JTextField quickSearch) {
		this.quickSearch = quickSearch;
	}

	public void processQuickSearch() {
		String prefix = quickSearch.getText();
		for (int i = 0; i < list.getModel().getSize(); i++) {
			if (((String)list.getModel().getElementAt(i)).startsWith(prefix)) {
				list.setSelectedIndex(i);
				list.ensureIndexIsVisible(i);
				break;
			}
		}
	}
}
