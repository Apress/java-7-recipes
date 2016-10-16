
package org.java7recipes.chapter2.recipe2_03;

/**
 * Recipe 2-3
 * 
 * Trimming Whitespace
 * 
 * @author juneau
 */
public class StringExamples {
    
    
    
    public static void main(String[] args){

       trimString();
       
    }
   
    public static void trimString(){
        String myString = " This is a String that contains whitespace.   ";
        
        System.out.println(myString);
        System.out.println(myString.trim());
    }
   
}
