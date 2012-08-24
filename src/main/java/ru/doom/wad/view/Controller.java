package ru.doom.wad.view;

import com.google.inject.Singleton;

import javax.swing.*;

@Singleton
public class Controller {

	private int openedFilesCount;
	private JFrame frame;
	private JProgressBar progressBar;
	private JList list;

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
}
