
package org.java7recipes.chapter1.recipe1_13;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/**
 * Recipe 1-13
 * 
 * @author juneau
 */
public class GreetingMessage {
    public static void main(String[] args){
        BufferedReader readIn = new BufferedReader(new InputStreamReader(System.in));
        String username = "";
        System.out.println("Please enter your username: ");
        try{
            username = readIn.readLine();
            System.out.println("Your username is " + username);
        } catch (IOException ex){
            System.out.println(ex);
        }
       
    }
    
}
