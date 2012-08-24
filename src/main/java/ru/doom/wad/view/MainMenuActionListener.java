package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import ru.doom.wad.logic.task.OpenWadTask;
import ru.doom.wad.logic.task.TaskProvider;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

@Singleton
public class MainMenuActionListener implements ActionListener {

	@Inject
	private DialogManager dialogManager;
	@Inject
	private Controller controller;
	@Inject
	private TaskProvider taskProvider;

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem)e.getSource();
			JPopupMenu popupMenu = (JPopupMenu)source.getParent();
			JFrame frame = (JFrame)((JComponent)popupMenu.getInvoker()).getTopLevelAncestor();
			if ("Open".equals(e.getActionCommand())) {
				final File file = dialogManager.selectOpenFile(frame);
				new Thread(taskProvider.get().withFile(file)).start();
			}
		}		
	}
}
