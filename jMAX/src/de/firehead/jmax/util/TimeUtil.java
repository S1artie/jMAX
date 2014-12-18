package de.firehead.jmax.util;

import java.util.Calendar;
import java.util.Date;

public final class TimeUtil {

	private TimeUtil() {
		// private constructor
	}

	public static Date parseCubeDateAndTime(int aCubeDate, int aCubeTime) {
		int year = ((aCubeDate & 0x00FF0000) >> 16) + 2000;
		int month = ((aCubeDate & 0x0000FF00) >> 8);
		int day = (aCubeDate & 0x000000FF);
		int hours = ((aCubeTime & 0xFF00) >> 8);
		int minutes = (aCubeTime & 0x00FF);

		Calendar calendar = Calendar.getInstance();
		calendar.clear();
		calendar.set(year, month - 1, day, hours, minutes);
		return calendar.getTime();
	}

}
