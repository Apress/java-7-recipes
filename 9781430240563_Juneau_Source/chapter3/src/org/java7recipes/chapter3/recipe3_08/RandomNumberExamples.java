
package org.java7recipes.chapter3.recipe3_08;

import java.util.Random;

/**
 * Recipe 3-8
 * 
 * Randomly Generating Values
 * 
 * @author juneau
 */
public class RandomNumberExamples {

    public static void main(String[] args){
        randomExamples();
    }

    public static void randomExamples() {
        // Create a new instance of the Random class
        Random random = new Random();

// Generates a random Integer
        int myInt = random.nextInt();

// Generates a random Double value
        double myDouble = random.nextDouble();

// Generates a random float
        float myFloat = random.nextFloat();

// Generates a random Gaussian double
// mean 0.0 and standard deviation 1.0 
// from this random number generator's sequence.
        double gausDouble = random.nextGaussian();

// Generates a random Long
        long myLong = random.nextLong();

// Generates a random boolean
        boolean myBoolean = random.nextBoolean();
        
        double rand = Math.random();

        
    }
}
