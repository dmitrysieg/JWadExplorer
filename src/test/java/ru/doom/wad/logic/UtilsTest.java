package ru.doom.wad.logic;

import org.junit.Assert;
import org.junit.Test;

public class UtilsTest {

	@Test
	public void testTrimNull() throws Exception {
		Assert.assertEquals("F_END", new Utils().trimNull("F_END\u0000\u0000\u0000"));
	}
}
