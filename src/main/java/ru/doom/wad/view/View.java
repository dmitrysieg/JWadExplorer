package ru.doom.wad.view;

import org.springframework.stereotype.Component;

import javax.swing.*;

@Component
public class View {

	private JFrame frame;
	private JTabbedPane tabbedPane;

	private WorkspaceView currentWorkspace;

	private JButton saveFileButton;
	private JButton saveImageButton;

	/* */

	public JFrame getFrame() {
		return frame;
	}

	public void setFrame(JFrame frame) {
		this.frame = frame;
	}

	public JTabbedPane getTabbedPane() {
		return tabbedPane;
	}

	public void setTabbedPane(JTabbedPane tabbedPane) {
		this.tabbedPane = tabbedPane;
	}

	public WorkspaceView getCurrentWorkspace() {
		return currentWorkspace;
	}

	public void setCurrentWorkspace(WorkspaceView currentWorkspace) {
		this.currentWorkspace = currentWorkspace;
	}

	public JButton getSaveFileButton() {
		return saveFileButton;
	}

	public void setSaveFileButton(JButton saveFileButton) {
		this.saveFileButton = saveFileButton;
	}

	public JButton getSaveImageButton() {
		return saveImageButton;
	}

	public void setSaveImageButton(JButton saveImageButton) {
		this.saveImageButton = saveImageButton;
	}
}
