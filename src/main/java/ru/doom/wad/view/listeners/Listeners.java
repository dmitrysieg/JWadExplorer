package ru.doom.wad.view.listeners;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Listeners {

	@Autowired private MainMenuActionListener mainMenuActionListener;
	@Autowired private QuickSearchActionListener quickSearchActionListener;
	@Autowired private WadListMouseListener wadListMouseListener;
	@Autowired private PopupMenuListener popupMenuListener;
	@Autowired private ToolbarActionListener toolbarActionListener;
	@Autowired private TabCloseListener tabCloseListener;
	@Autowired private TabChangeListener tabChangeListener;

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

	public ToolbarActionListener getToolbarActionListener() {
		return toolbarActionListener;
	}

	public TabCloseListener getTabCloseListener() {
		return tabCloseListener;
	}

	public TabChangeListener getTabChangeListener() {
		return tabChangeListener;
	}
}
