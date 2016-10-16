
package org.java7recipes.chapter3.recipe3_15;

/**
 * Recipe 3-15
 * 
 * Binary Literals
 * 
 * @author juneau
 */
public class BinaryLiteralExample {

    public static void main(String[] args) {
        int bin1 = 0b1100;
        short bin2 = 0B010101;
        short bin3 = (short) 0b1001100110011001;

        System.out.println(bin1);
        System.out.println(bin2);
        System.out.println(bin3);


    }
}
