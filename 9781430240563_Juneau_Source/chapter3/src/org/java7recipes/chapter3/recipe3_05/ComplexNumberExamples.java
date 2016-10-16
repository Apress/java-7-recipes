
package org.java7recipes.chapter3.recipe3_05;

import java.util.List;
import org.apache.commons.math.complex.Complex;

/**
 * Recipe 3-5
 * 
 * Performing Calculations with Complex Numbers
 * 
 * @author juneau
 */
public class ComplexNumberExamples {

    public static void main(String[] args) {
        complexArithmetic();
    }

    public static void complexArithmetic() {

        // Create complex numbers by passing two floats to the Complex class
        Complex complex1 = new Complex(8.0, 3.0);
        Complex complex2 = new Complex(4.2, 5.0);
        Complex complex3 = new Complex(8.7, 13.53);
        Complex result;

        // Find the abosolute value of a complex number
        double absresult = complex1.abs();

        // Compute the exponential function
        Complex exp = complex1.exp();

        // Add two complex numbers together
        result = complex1.add(complex2);

        // Subtract two complex numbers
        result = complex2.subtract(complex3);

        // Divide two complex numbers
        result = complex2.divide(complex3);

        // Multiply two complex Numbers
        result = complex1.multiply(complex2);

        // Multiply a complex number and a double
        result = complex1.multiply(absresult);

        // Return the additive inverse of a given complex number
        result = complex1.negate();

        // Return the list of the 5th roots for this complex number
        List nth = complex1.nthRoot(5);

        // Computes complex number raised to the power of another
        Complex pow = complex1.pow(complex2);

        // Computes the square root of the complex number
        Complex sqrt = complex1.sqrt();

        // Retrieve the real and imaginary parts of the result
        double real = result.getReal();
        double imag = result.getImaginary();

        // Obtain the tangent
        result = complex1.tan();

    }

   
}
