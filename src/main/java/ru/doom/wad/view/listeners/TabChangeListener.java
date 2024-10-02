package ru.doom.wad.view.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.view.Controller;
import ru.doom.wad.view.View;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@Component
public class TabChangeListener implements ChangeListener {

    @Autowired private View view;
    @Autowired private Controller controller;

    @Override
    public void stateChanged(ChangeEvent e) {
        if (controller.isTabBeingCreated()) {
            return;
        }
        controller.changeTab(view.getTabbedPane().getSelectedIndex());
        //view.getCurrentWorkspace().getPanel().repaint();
        view.getTabbedPane().repaint();
        controller.adjustAnyPaletteExist();
    }
}
