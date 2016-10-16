
package org.java7recipes.chapter3.recipe3_12;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Recipe 3-12
 * 
 * Formatting Dates for Display
 * 
 * @author juneau
 */
public class DateExamples {

    public static void main(String[] args) {
       formatExamples();
    }
   
    public static void formatExamples(){
        
        // Create new calendar
        Calendar cal = Calendar.getInstance();

        // Create instance of SimpleDateFormat class using pattern
        SimpleDateFormat dateFormatter1 = new SimpleDateFormat("MMMMM dd yyyy");
        String result = null;

        result = dateFormatter1.format(cal.getTime());
        System.out.println(result);

        dateFormatter1.applyPattern("MM/dd/YY hh:mm:ss");
        result = dateFormatter1.format(cal.getTime());
        System.out.println(result);

        dateFormatter1.applyPattern("hh 'o''clock' a, zzzz");
        result = dateFormatter1.format(cal.getTime());
        System.out.println(result);
    }
    
   
}
