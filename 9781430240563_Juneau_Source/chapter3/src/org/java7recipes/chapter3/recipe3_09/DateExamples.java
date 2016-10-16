
package org.java7recipes.chapter3.recipe3_09;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;

/**
 * Recipe 3-9
 * 
 * Obtaining the Current Date
 * 
 * @author juneau
 */
public class DateExamples {

    public static void main(String[] args) {
       newDate();
        calendarExamples();
    }

    public static void newDate() {
        Date date = new Date();
        System.out.println(date);
        System.out.println(date.getTime());
    }

    public static void calendarExamples() {
        Calendar gCal = Calendar.getInstance();

        // Month is based upon a zero index, January is equal to 0,
        // so we need to add one to the month for it to be in 
        // a standard format
        int month = gCal.get(Calendar.MONTH) + 1;
        int day = gCal.get(Calendar.DATE);
        int yr = gCal.get(Calendar.YEAR);

        String dateStr = month + "/" + day + "/" + yr;
        System.out.println(dateStr);

        int dayOfWeek = gCal.get(Calendar.DAY_OF_WEEK);

        // Print out the integer value for the day of the week
        System.out.println(dayOfWeek);

        int hour = gCal.get(Calendar.HOUR);
        int min = gCal.get(Calendar.MINUTE);
        int sec = gCal.get(Calendar.SECOND);


        // Print out the time
        System.out.println(hour + ":" + min + ":" + sec);

        // Create new DateFormatSymbols instance to obtain the String
        // value for dates
        DateFormatSymbols symbols = new DateFormatSymbols();
        String[] days = symbols.getWeekdays();
        System.out.println(days[dayOfWeek]);

        // Get crazy with the date!
        int dayOfYear = gCal.get(Calendar.DAY_OF_YEAR);
        System.out.println(dayOfYear);

        // Print the number of days left in the year
        System.out.println("Days left in " + yr + ": " + (365 - dayOfYear));

        int week = gCal.get(Calendar.WEEK_OF_YEAR);
        // Print the week of the year
        System.out.println(week);

    } 
}
