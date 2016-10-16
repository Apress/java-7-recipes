
package org.java7recipes.chapter3.recipe3_11;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Recipe 3-11
 * 
 * Finding the Difference Between Two Dates
 * 
 * @author juneau
 */
public class DateExamples {

    public static void main(String[] args) {
        dateDiff();
    }

    
    public static void dateDiff(){
        // Obtain two instances of the Calendar class
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Set the date to 01/01/2010:12:00
        cal2.set(2010,0,1,12,0);
        Date date1 = cal2.getTime();
        System.out.println(date1);

        long mill = Math.abs(cal1.getTimeInMillis() - date1.getTime());
        // Convert to hours
        long hours = TimeUnit.MILLISECONDS.toHours(mill);
        // Convert to days
        Long days = TimeUnit.HOURS.toDays(hours);
        String diff = String.format("%d hour(s) %d min(s)", hours,
        TimeUnit.MILLISECONDS.toMinutes(mill) - TimeUnit.HOURS.toMinutes(hours));
        System.out.println(diff);

        diff = String.format("%d days", days);
        System.out.println(diff);

        // Divide the number of days by seven for the weeks
        int weeks = days.intValue()/7;
        diff = String.format("%d weeks", weeks);
        System.out.println(diff);

    }
}
