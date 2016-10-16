
package org.java7recipes.chapter2.recipe2_05;

/**
 * Recipe 2-5
 * 
 * Concatenating Strings
 * 
 * @author juneau
 */
public class StringExamples {
    
    
    
    public static void main(String[] args){

       concatExample();
       concatOperatorExample();
       stringBufferExample();
       
    }
   
    
    public static void concatExample(){
        String one = "Hello";
        String two = "Java7";
        String result = one.concat(" ".concat(two.substring(0, two.length())));
        
        System.out.println(result);
    }
    
    public static void concatOperatorExample(){
        String one = "Hello";
        String two = "Java7";
        String result = one + " " + two;
        
        System.out.println(result);
    }
    
    public static void stringBufferExample(){
        String one = "Hello";
        String two = "Java7";
        StringBuffer buffer = new StringBuffer();
        buffer.append(one).append(" ").append(two);

        String result = buffer.toString();

        System.out.println(result);
    }
}