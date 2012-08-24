package ru.doom.wad.logic.task;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.Singleton;

@Singleton
public class TaskProvider implements Provider<OpenWadTask> {

	@Inject
	private Injector injector;

	@Override
	public OpenWadTask get() {
		return injector.getInstance(OpenWadTask.class);
	}
}
