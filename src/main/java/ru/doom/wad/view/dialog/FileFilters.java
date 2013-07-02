package ru.doom.wad.view.dialog;

import com.google.inject.Singleton;

import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.util.regex.Pattern;

@Singleton
public final class FileFilters {

	public static final FileFilter GIF_FILE_FILTER = createFileFilter("gif");
	public static final FileFilter WAD_FILE_FILTER = createFileFilter("wad");

	private static FileFilter createFileFilter(final String extension) {
		final String lowerExtension = extension.toLowerCase();
		final String upperExtension = extension.toUpperCase();
		return new FileFilter() {
			private final Pattern REGEXP = Pattern.compile(".*?" + lowerExtension + "$", Pattern.CASE_INSENSITIVE);
			@Override public boolean accept(File f) {return f.isDirectory() || REGEXP.matcher(f.getName()).matches();}
			@Override public String getDescription() {return upperExtension + " Files (*." + lowerExtension + ")";}
		};
	}
}
