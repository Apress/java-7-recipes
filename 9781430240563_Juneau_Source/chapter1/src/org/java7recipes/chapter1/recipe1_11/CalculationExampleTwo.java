
package org.java7recipes.chapter1.recipe1_11;

/**
 *
 * @author juneau
 */
public class CalculationExampleTwo {
    
    public static void main(String[] args){
        int num1  = 0, num2 = 0;
        
        if (args.length > 1){
            num1 = Integer.valueOf(args[0]);
            num2 = Integer.valueOf(args[1]);
        }
        // Call the addNumbers method
        addNumbers(num1, num2);
        // Call the multiplyNumbers method
        System.out.println("The product of num1 and num2 is " +
                multiplyNumbers(num1, num2));
        
    }
    
    /**
     * Adds num1 and num2, then prints the sum.
     */
    public static void addNumbers(int num1, int num2){
        int sum = num1 + num2;
        System.err.println("The sum of num1 and num2 is " + sum);
    }
    
    /**
     * Multiplies num1 and num2 then prints the product.
     */
    public static int multiplyNumbers(int num1, int num2){
        int product = num1 * num2;
        return product;
    }
}
