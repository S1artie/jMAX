package de.firehead.jmax.util;

public final class StringUtil {

	private StringUtil() {
		// private constructor
	}

	public static String formatRFAddress(int anAddress) {
		return String.format("%06X", anAddress);
	}

}
