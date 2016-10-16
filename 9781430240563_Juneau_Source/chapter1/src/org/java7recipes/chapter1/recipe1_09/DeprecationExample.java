
package org.java7recipes.chapter1.recipe1_09;

import org.java7recipes.chapter1.recipe1_08.*;
import java.math.BigInteger;

/**
 * Recipe 1-9
 * 
 * This example demonstrates deprecation of methods.
 * 
 * @author juneau
 */
public class DeprecationExample {
    
    public static void main(String[] args){
        BigInteger[] arr = new BigInteger[2];
        arr[0] = new BigInteger("1");
        arr[1] = new BigInteger("25");
        // Use the older, deprecated method
        System.out.println(addNumbers(1, 25));
        // Use the newer, non-deprecated method
        System.out.println(addNumbers(arr));
    }
    
    /**
     * Accepts two values and returns their sum.
     * 
     * @param x
     * @param y
     * @return
     * @deprecated The newer, more robust addNumbers(BigInteger[]) should
     *             now be used
     */
    @Deprecated
    public static int addNumbers(int x, int y){
        return x + y;
    }
    
    /**
     * Newer, better method that accepts an unlimited number of values and
     * returns the sum.
     * 
     * @param nums
     * @return 
     */
    public static BigInteger addNumbers(BigInteger[] nums){
        BigInteger result = new BigInteger("0");
        for (BigInteger num:nums){
            result = result.add(num);
        }
        
        return result;
    }
    
}
