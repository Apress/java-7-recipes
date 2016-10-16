/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.java7recipes.chapter1.recipe1_03;

/**
 *
 * @author juneau
 */
public class MainTestArguments {
    public static void main(String[] args){
        if(args.length > 0){
            System.out.println("Arguments that were passed to the program: ");
            for (String arg:args){
                System.out.println(arg);
            }
        } else {
            System.out.println("No arguments passed to the program.");
        }
    }
}
