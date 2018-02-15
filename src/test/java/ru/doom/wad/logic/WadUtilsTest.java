package ru.doom.wad.logic;

import org.junit.Assert;
import org.junit.Test;
import ru.doom.wad.logic.format.wad.WadUtils;

public class WadUtilsTest {

	@Test
	public void testTrimNull() throws Exception {
		Assert.assertEquals("F_END", new WadUtils().trimNull("F_END\u0000\u0000\u0000"));
	}
}
