package com.sporty.shoes.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Service;

@Service
public class ServiceUtil {

	public static Date convertToDate(String dateInString) {
		String[] dateAndTimeArr = dateInString.split(" ");
		
		String dateStr = dateAndTimeArr[0];
		String[] dateArr = dateStr.split("-");
		int year = Integer.parseInt(dateArr[0]);
		int month = Integer.parseInt(dateArr[1])-1;
		int date = Integer.parseInt(dateArr[2]);
		
		
		String timeStr = dateAndTimeArr[1];
		String[] timeArr = timeStr.split(":");
		int hours = Integer.parseInt(timeArr[0]);
		int minutes = Integer.parseInt(timeArr[1]);
		int seconds = Integer.parseInt(timeArr[2]);
		
		Calendar calendar = Calendar.getInstance();
		
			if(hours > 12) {
				calendar.set(Calendar.HOUR, hours - 12);
				calendar.set(Calendar.AM_PM, Calendar.PM);					
			} else {
				calendar.set(Calendar.HOUR, hours);
				calendar.set(Calendar.AM_PM, Calendar.AM);					
			}
			calendar.set(Calendar.MINUTE, minutes);
			calendar.set(Calendar.SECOND, seconds);
			calendar.set(Calendar.YEAR, year);
			calendar.set(Calendar.MONTH, month);
			calendar.set(Calendar.DATE, date);
			return calendar.getTime();
			
		}

	public static boolean isValidFormat(String format, String value) {
		Date date = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			date = sdf.parse(value);
			if (!value.equals(sdf.format(date))) {
				date = null;
			}
		} catch (ParseException ex) {
			ex.printStackTrace();
		}
		return date != null;
	}
}
