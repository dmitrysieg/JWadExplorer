package ru.doom.wad.view.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.view.Controller;
import ru.doom.wad.view.EditorTab;
import ru.doom.wad.view.ViewManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class TabCloseListener implements ActionListener {

    @Autowired private ViewManager viewManager;
    @Autowired private Controller controller;

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        JPanel tab = viewManager.findTabPanel(btn.getParent());
        final EditorTab editorTab = controller.findEditorTab(tab);
        controller.removeEditorTab(editorTab.getAbsolutePath());
    }
}
