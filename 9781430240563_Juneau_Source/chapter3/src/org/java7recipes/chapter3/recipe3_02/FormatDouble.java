
package org.java7recipes.chapter3.recipe3_02;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * Recipe 3-2
 * 
 * Formatting double and long Decimal Values
 * 
 * @author juneau
 */
public class FormatDouble {

    public static void main(String[] args) {
        formatDouble(new Double("345.9372"));
    }

    public static void formatDouble(double myDouble) {
        NumberFormat numberFormatter = new DecimalFormat("##.000");
        String result = numberFormatter.format(myDouble);

        System.out.println(result);

        // Obtains an instance of NumberFormat class
        NumberFormat format = NumberFormat.getInstance();

// Format a double value for the current locale
        String result2 = format.format(83.404);
        System.out.println(result2);

// Format a double value for an Italian locale
        result = format.getInstance(Locale.ITALIAN).format(83.404);
        System.out.println(result2);

// Parse a String into a Number
        try {
            Number num = format.parse("75.736");
            System.out.println(num);
        } catch (java.text.ParseException ex) {
            System.out.println(ex);
        }

    }
}
