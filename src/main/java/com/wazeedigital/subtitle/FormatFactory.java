package com.wazeedigital.subtitle;

import java.util.Arrays;
import java.util.List;

/**
 * Encapsulates determining what file types are supported and generating appropriate formatters for them.
 * 
 * TTML: Note that this assumes that TTML files can end in either ".ttml" or ".xml", but not all XML files are TTML.
 * Just be careful when processing XML because we could easily claim to support it, but then find out it's not TTML once
 * we look at the stream and error out.
 * 
 * @author kmurray
 */
public class FormatFactory {
	public static final List<String> supportedFormats = Arrays.asList("srt", "stl", "scc", "ass", "ssa", "xml", "ttml");

	/**
	 * Given a file name (or just the extension), determines if it looks like a sub-title file we can read
	 * 
	 * @param fileNameOrExtension File name (herkimer.scc) or extension (.scc or scc)
	 * @return {@code true} if we think we can read this file type. {@code false} if it does not look like a sub-title
	 * file
	 */
	public static boolean isSupportedFormat(String fileNameOrExtension) {
		if (fileNameOrExtension == null) return false;

		// get the extension (works even if there is no dot)
		String ext = fileNameOrExtension.substring(fileNameOrExtension.lastIndexOf('.') + 1);
		return supportedFormats.contains(ext.toLowerCase());
	}

	/**
	 * Given a file name (or just an extension), gets a formatter for that file type
	 * 
	 * @param fileNameOrExtension File name (herkimer.scc) or extension (.scc or scc)
	 * @return A formatter for the file type or {code null} if one is not available
	 * @throws Exception
	 */
	public static TimedTextFileFormat getFormatForFile(String fileNameOrExtension) {
		if (fileNameOrExtension == null) return null;

		// get the extension (works even if there is no dot)
		String ext = fileNameOrExtension.substring(fileNameOrExtension.lastIndexOf('.') + 1).toLowerCase();

		switch (ext) {
		case "srt":
			return new FormatSRT();
		case "stl":
			return new FormatSTL();
		case "scc":
			return new FormatSCC();
		case "ass":
		case "ssa":
			return new FormatASS();
		case "xml":
		case "ttml":
			return new FormatTTML();
		default:
			return null;
		}
	}
}
