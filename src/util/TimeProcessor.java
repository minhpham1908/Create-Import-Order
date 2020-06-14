package util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

public class TimeProcessor {
	public static LocalDateTime UnixToLocalDateTime(int timestamp) {
		Instant instant = Instant.ofEpochSecond(timestamp);
		LocalDateTime localDateTime = LocalDateTime.ofInstant(instant,ZoneId.systemDefault());
//		LocalDate dtDateTime = new LocalDate(1970,1,1,0,0,0,0,System.DateTimeKind.Utc);
//		    dtDateTime = dtDateTime.AddSeconds( unixTimeStamp ).ToLocalTime();
		    return localDateTime;
	}
	public static String getStringLocalDateTime(LocalDateTime dateTime, String pattern) {
		return dateTime.format(DateTimeFormatter.ofPattern(pattern));
	}
	
}
