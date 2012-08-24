package ru.doom.wad;

import com.google.inject.Guice;
import com.google.inject.Injector;
import ru.doom.wad.view.Controller;
import ru.doom.wad.view.ViewManager;

import javax.swing.*;

public class App {

	public static void main(String[] args) {

		final Injector injector = Guice.createInjector(new AppModule());

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame mainFrame = injector.getInstance(ViewManager.class).createMainFrame();
				injector.getInstance(Controller.class).setFrame(mainFrame);
			}
		});
	}
}
