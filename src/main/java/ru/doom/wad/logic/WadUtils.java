package ru.doom.wad.logic;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@Singleton
public class WadUtils {

	@Inject
	private Injector injector;

	public WadEntry findByName(Wad wad, String name) {
		return Iterables.find(wad, injector.getInstance(NameMatcher.class).ofName(name));
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

	private static class NameMatcher implements Predicate<WadEntry> {

		@Inject
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
		public boolean apply(/*@Nullable*/WadEntry wadEntry) {
			return wadEntry != null && name.equals(wadUtils.trimNull(new String(wadEntry.getName())));
		}
	}
}
