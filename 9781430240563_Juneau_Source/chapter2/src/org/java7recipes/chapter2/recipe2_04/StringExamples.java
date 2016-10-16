
package org.java7recipes.chapter2.recipe2_04;

/**
 * Recipe 2-4
 * 
 * Changing the Case of a String
 * 
 * @author juneau
 */
public class StringExamples {
    
    
    
    public static void main(String[] args){
        changeCase();
    }
    
    public static void changeCase(){
        String str = "This String will change case.";
        
        System.out.println(str.toUpperCase());
        System.out.println(str.toLowerCase());
    }
    
 }