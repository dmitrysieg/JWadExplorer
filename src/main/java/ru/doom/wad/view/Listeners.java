package ru.doom.wad.view;

import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class Listeners {

	@Inject
	private MainMenuActionListener mainMenuActionListener;
	@Inject
	private QuickSearchActionListener quickSearchActionListener;
	@Inject
	private WadListMouseListener wadListMouseListener;
	@Inject
	private PopupMenuListener popupMenuListener;

	public MainMenuActionListener getMainMenuActionListener() {
		return mainMenuActionListener;
	}

	public QuickSearchActionListener getQuickSearchActionListener() {
		return quickSearchActionListener;
	}

	public WadListMouseListener getWadListMouseListener() {
		return wadListMouseListener;
	}

	public PopupMenuListener getPopupMenuListener() {
		return popupMenuListener;
	}
}
