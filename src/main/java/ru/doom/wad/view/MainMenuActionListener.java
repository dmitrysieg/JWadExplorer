package ru.doom.wad.view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuActionListener implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() instanceof JMenuItem) {
			JMenuItem source = (JMenuItem)e.getSource();
			JPopupMenu popupMenu = (JPopupMenu)source.getParent();
			JFrame frame = (JFrame)((JComponent)popupMenu.getInvoker()).getTopLevelAncestor();
			if ("Open".equals(e.getActionCommand())) {
				DialogManager.selectOpenFile(frame);
			}
		}		
	}
}
