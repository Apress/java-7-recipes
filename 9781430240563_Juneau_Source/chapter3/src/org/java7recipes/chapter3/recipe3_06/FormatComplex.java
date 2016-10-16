
package org.java7recipes.chapter3.recipe3_06;

import java.text.NumberFormat;
import java.text.ParseException;
import org.apache.commons.math.complex.Complex;
import org.apache.commons.math.complex.ComplexFormat;

/**
 * Recipe 3-6
 * 
 * Formatting and Parsing Complex Numbers
 * 
 * @author juneau
 */
public class FormatComplex {
    
    public static void main(String[] args){
        formatComplex();
        Complex returnedComplex = parseComplex("2.837 + 83.9i");
    }
    
     public static void formatComplex() {
        ComplexFormat format = new ComplexFormat(); // default format
        Complex c = new Complex(3.1415, 7.846);
        String s = format.format(c); 
        System.out.println(s);  // prints = 3.14 + 7.85i

        NumberFormat numformat = NumberFormat.getInstance();
        numformat.setMinimumFractionDigits(3);
        numformat.setMaximumFractionDigits(3);

        ComplexFormat format2 = new ComplexFormat(numformat);
        s = format2.format(c);
        System.out.println(s); // prints:  3.142 + 7.846i

    }
    
    public static Complex parseComplex(String complex){
        ComplexFormat cf = new ComplexFormat();
        Complex complexNum = null;
        try {
            complexNum = cf.parse("1.110 + 2.222i");
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        return complexNum;
    }
}
