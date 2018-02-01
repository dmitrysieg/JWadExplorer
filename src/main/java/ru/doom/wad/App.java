package ru.doom.wad;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.doom.wad.view.ViewManager;

import javax.swing.*;

public class App {

	public static void main(String[] args) {

		final ApplicationContext context = new AnnotationConfigApplicationContext("ru.doom.wad");
		SwingUtilities.invokeLater(() -> context.getBean(ViewManager.class).createMainFrame());
	}
}
