package extraprojects;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class SpecialMath 
{	
	public static int randomNumberGen(int lowerIndex, int upperIndex) {
		long randomModifier = 165964752320553L;
		if (lowerIndex>=upperIndex) {
			System.out.println("Invalid argument given.");
			return 0;
		} else {
			String intTableString;
			int temp;
			long intTable = (randomModifier)*(convertSecondsOfTimeToInt()+1)*upperIndex*(convertMinutesOfTimeToInt()+convertHoursOfTimeToInt()+1);
			int digits = String.valueOf(upperIndex).length(); 
			if (intTable<0) {intTable *= -1;}
			intTableString = String.valueOf(intTable);
			while (1==1) {
				System.out.println(intTableString);
				temp = Integer.parseInt(intTableString.substring(0, digits));
				if (temp>=lowerIndex&&temp<=upperIndex) {
					return temp;
				} else {intTableString = intTableString.substring(digits);}
			}
		}
	}
	public static String getCurrentTimeUsingCalendar() {
	    Calendar cal = Calendar.getInstance();
	    Date date=cal.getTime();
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    String formattedDate=dateFormat.format(date);
	    return formattedDate;
	}
	public static int convertSecondsOfTimeToInt() {
		String timeString = getCurrentTimeUsingCalendar().substring(6);
		return Integer.parseInt(timeString);
	}
	public static int convertMinutesOfTimeToInt() {
		String timeString = getCurrentTimeUsingCalendar().substring(3,5);
		return Integer.parseInt(timeString);
	}	public static int convertHoursOfTimeToInt() {
		String timeString = getCurrentTimeUsingCalendar().substring(0,2);
		return Integer.parseInt(timeString);
	}
}
