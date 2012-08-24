package ru.doom.wad.logic;

import org.junit.Test;
import ru.doom.wad.logic.IWadReader;

import java.io.File;

public class IWadReaderTest {

	@Test
	public void testRead() throws Exception {
		new IWadReader().read(new File("c:/games/doom2/doom2.wad"));
	}
}
