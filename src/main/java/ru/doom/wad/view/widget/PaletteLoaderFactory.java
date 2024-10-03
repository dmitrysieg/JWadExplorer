package ru.doom.wad.view.widget;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.doom.wad.view.Controller;
import ru.doom.wad.view.DialogManager;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Component
public class PaletteLoaderFactory {

    @Autowired private Controller controller;
    @Autowired private DialogManager dialogManager;

    public PaletteLoader getPaletteLoader() {
        return new PaletteLoader();
    }

    public class PaletteLoader {

        private JPanel paletteLoaderPanel;
        private JButton button;

        public PaletteLoader() {
            paletteLoaderPanel = new JPanel();
            paletteLoaderPanel.setLayout(new BoxLayout(paletteLoaderPanel, BoxLayout.PAGE_AXIS));
            paletteLoaderPanel.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));

            final JLabel label = new JLabel("Palette not found");
            paletteLoaderPanel.add(label, BorderLayout.CENTER);

            button = new JButton("Load palette..");
            button.addActionListener(new PaletteLoaderBtnListener());
            paletteLoaderPanel.add(button, BorderLayout.CENTER);
        }

        public JPanel getPane() {
            return paletteLoaderPanel;
        }

        public JButton getButton() {
            return button;
        }
    }

    private class PaletteLoaderBtnListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            dialogManager.showPaletteLoaderDialog(controller.getPaletteTabList());
        }
    }
}
