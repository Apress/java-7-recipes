
package org.java7recipes.chapter3.recipe3_13;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Recipe 3-13
 * 
 * Comparing Dates
 * 
 * @author juneau
 */
public class DateExamples {

    public static void main(String[] args){
        compareDates();
    }
    
    public static void compareDates() {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();

        // Set the date to 01/01/2010:12:00
        cal2.set(2010,0,1,12,0);

        System.out.println(formatDate(cal1) + " before " + formatDate(cal2) + "? " + cal1.before(cal2));
        System.out.println(cal2.compareTo(cal1));

    }
    
    /**
     * Date formatting method that accepts a Calendar object and returns
     * a formatted String.
     * 
     * @param cal
     * @return 
     */
    public static String formatDate(Calendar cal) {
        SimpleDateFormat simpleFormatter = new SimpleDateFormat("MMM dd yyyy hh:mm:ss aaa");

        return simpleFormatter.format(cal.getTime());

    }
}
