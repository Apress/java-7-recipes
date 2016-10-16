
package org.java7recipes.chapter3.recipe3_10;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Recipe 3-10
 * 
 * Adding and Subtracting Days, Months, and Years
 * 
 * @author juneau
 */
public class DateExamples {

    public static void main(String[] args) {
       calculateDates();
    }

    

    public static void calculateDates() {
        Calendar cal = Calendar.getInstance();
        String monthStr = null;
        // Note:  month values range from 0 to 11...so add one to the number

        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DATE);
        int yr = cal.get(Calendar.YEAR);

        System.out.println("January = " + Calendar.JANUARY);
        System.out.println("June = " + Calendar.JUNE);

        System.out.println("Current Date: " + formatDate(cal));

        // Add two months to current date
        cal.add(Calendar.MONTH, 2);

        System.out.println("Current Date Plus 2 Months: " + formatDate(cal));

        cal = Calendar.getInstance();

        // Subtract 8 months from current date
        cal.add(Calendar.MONTH, -8);

        System.out.println("Current Date Minus 8 Months: " + formatDate(cal));

        cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -8);
        System.out.println("Current Date Minus 8 Days: " + formatDate(cal));

        // Add 15 hours to current date
        cal = Calendar.getInstance();
        cal.add(Calendar.HOUR, 15);
        System.out.println("Current Date Plus 15 Hours: " + formatDate(cal));
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
