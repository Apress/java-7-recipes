
package org.java7recipes.chapter1.recipe1_10;

/**
 * Recipe 1-10
 * 
 * @author juneau
 */
public class CalculationExampleOne {
    
    static int num1 = 0;
    static int num2 = 4;
    
    public static void main(String[] args){
        if (args.length > 1){
            num1 = Integer.valueOf(args[0]);
            num2 = Integer.valueOf(args[1]);
        }
        // Call the addNumbers method
        addNumbers();
        // Call the multiplyNumbers method
        multiplyNumbers();
        
    }
    
    /**
     * Adds num1 and num2, then prints the sum.
     */
    public static void addNumbers(){
        int sum = num1 + num2;
        System.err.println("The sum of num1 and num2 is " + sum);
    }
    
    /**
     * Multiplies num1 and num2 then prints the product.
     */
    public static void multiplyNumbers(){
        int product = num1 * num2;
        System.out.println("The product of num1 and num2 is " + product);
    }
    
}
