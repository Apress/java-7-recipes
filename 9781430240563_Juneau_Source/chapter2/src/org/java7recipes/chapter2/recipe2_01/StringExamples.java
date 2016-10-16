
package org.java7recipes.chapter2.recipe2_01;

/**
 * Recipe 2-1
 * 
 * Obtaining a subsection of a string
 * 
 * @author juneau
 */
public class StringExamples {
    
    
    
    public static void main(String[] args){

        
       substringExample();

    }
    
    public static void substringExample(){
        String originalString = "This is the original String";
        System.out.println(originalString.substring(0, originalString.length()));
        System.out.println(originalString.substring(5, 20));
        System.out.println(originalString.substring(12));
    }
    
    
   
}
