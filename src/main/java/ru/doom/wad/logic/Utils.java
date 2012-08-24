package ru.doom.wad.logic;

import com.google.inject.Singleton;

/**
 * todo: try to get rid of this bicycle
 */
@Singleton
public class Utils {

	public String trimNull(String string) {
		if (string.length() > 8) {
			throw new IllegalArgumentException("only dos-like filename strings is accepted");
		} else {
			int i = 7;
			for (; i > 0 && string.charAt(i) == 0; i--) {}
			return string.substring(0, i + 1);
		}
	}
}
