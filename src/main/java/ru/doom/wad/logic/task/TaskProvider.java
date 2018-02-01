package ru.doom.wad.logic.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class TaskProvider {

	@Autowired
	private ApplicationContext context;

	public OpenWadTask get() {
		return context.getBean(OpenWadTask.class);
	}
}
