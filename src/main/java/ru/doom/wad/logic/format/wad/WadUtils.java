package ru.doom.wad.logic.format.wad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Predicate;

@Component
public class WadUtils {

	@Autowired
	private ApplicationContext context;

	public Optional<WadEntry> findByName(Wad wad, String name) {
		return wad.stream().filter(context.getBean(NameMatcher.class).ofName(name)).findFirst();
	}

	/*
	 * todo: try to get rid of this bicycle
	 */
	public String trimNull(String string) {
		if (string.length() > 8) {
			throw new IllegalArgumentException("only dos-like filename strings is accepted");
		} else {
			int i = 7;
			for (; i > 0 && string.charAt(i) == 0; i--) {}
			return string.substring(0, i + 1);
		}
	}

	@Component
	@Scope("prototype")
	private static class NameMatcher implements Predicate<WadEntry> {

		@Autowired
		private WadUtils wadUtils;

		private String name;
		
		public NameMatcher ofName(String name) {
			if (name == null) {
				throw new IllegalArgumentException("null name is not allowed");
			}
			this.name = name;
			return this;
		}

		@Override
		public boolean test(/*@Nullable*/WadEntry wadEntry) {
			return wadEntry != null && name.equals(wadUtils.trimNull(new String(wadEntry.getName())));
		}
	}
}
