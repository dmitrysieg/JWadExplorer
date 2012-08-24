package ru.doom.wad.logic;

import org.junit.Test;
import ru.doom.wad.logic.IWadReader;

import javax.swing.*;
import java.io.File;

import static org.mockito.Mockito.mock;

public class IWadReaderTest {

	@Test
	public void testRead() throws Exception {
		new IWadReader(mock(JProgressBar.class)).read(new File("c:/games/doom2/doom2.wad"));
	}
}
