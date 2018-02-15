package ru.doom.wad.logic.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import ru.doom.wad.logic.format.Format;

@Component
public class TaskProvider {

	@Autowired
	private ApplicationContext context;

	public <T extends LoadFileTask> T get(final Format format) {
		return context.getBean((Class<T>) format.getTask());
	}
}
