package org.jtpd.util;

import java.util.Calendar;
import java.util.Date;

import org.apache.log4j.Logger;

public class DateUtils {

	private static Logger logger = Logger.getLogger("appLogger");

	public static String convertToWantedMonth(int month) {
		StringBuffer monthS = new StringBuffer();
		if (month < 10) {
			monthS.append("0" + month);
		} else {
			monthS.append(month);
		}

		if (logger.isDebugEnabled()) {
			logger.debug(" monthS.toString() " + monthS.toString());
		}
		return monthS.toString();
	}

	/**
	 * Return the date in YYYY-MM-DD-HH-MM format
	 * 
	 * @return
	 */
	public static String getNow() {

		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		String monthS = month + "";
		if (month < 10) {
			monthS = "0" + month;
		}

		int day = c.get(Calendar.DAY_OF_MONTH);
		String dayS = "" + day;
		if (day < 10) {
			dayS = "0" + day;
		}

		int hour = c.get(Calendar.HOUR_OF_DAY);
		String hourS = "" + hour;
		if (hour < 10) {
			hourS = "0" + hour;
		}

		int minute = c.get(Calendar.MINUTE);
		String minuteS = "" + minute;
		if (minute < 10) {
			minuteS = "0" + minute;
		}

		return year + "-" + monthS + "-" + dayS + "-" + hourS + "-" + minuteS;
	}

	public static String convertToFormattedDate(String date) {
		if (date != null && date.length() > 0) {
			int year = Integer.parseInt(date.substring(0, 4));
			int month = Integer.parseInt(date.substring(5, 7));
			String monthTurkish = translateToTurkishMonth(month);
			String day = date.substring(8, 10);
			return day + " " + monthTurkish + " " + year;
		} else {
			return "";
		}
	}

	public static String translateToTurkishMonth(int month) {

		switch (month) {
		case 1:
			return "Ocak";
		case 2:
			return "\u015eubat";
		case 3:
			return "Mart";
		case 4:
			return "Nisan";
		case 5:
			return "May\u0131s";
		case 6:
			return "Haziran";
		case 7:
			return "Temmuz";
		case 8:
			return "A\u011fustos";
		case 9:
			return "Eyl\u00fcl";
		case 10:
			return "Ekim";
		case 11:
			return "Kas\u0131m";
		case 12:
			return "Aral\u0131k";
		default:
			return "??";

		}
	}
	
	public static Date[] getDatesForLatestTopics() {
		
		Date[] dates = new Date[2];
		Calendar calendar = Calendar.getInstance();
		
		Date firstDate = new Date(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));		

		
		
		Date secondDate = new Date(calendar.get(Calendar.YEAR),
				calendar.get(Calendar.MONTH)-3,calendar.get(Calendar.DAY_OF_MONTH));		

		dates[0] = firstDate;
		dates[1] = secondDate;
		
		System.out.println(" firstDate " + firstDate +" " + firstDate.getYear());
		System.out.println(" secondDate " + secondDate +" " + secondDate.getYear() );
		
		return dates;
		
	}

    public static Date now(){
        return Calendar.getInstance().getTime();
    }
	
	public static void main(String[] args) {
		getDatesForLatestTopics();
	}
}
