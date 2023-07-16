package utopia.utils;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

	
	public static Date getCalendarDate() {

		Date date = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.getTime();
	}
	
}
