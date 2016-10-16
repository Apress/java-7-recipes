
package org.java7recipes.chapter2.recipe2_10;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Recipe 2-10
 * 
 * Determining If a File Name Ends with a Given String
 * 
 * @author juneau
 */
public class FindFileType {
    
    public static void main(String[] args) {
        findFileType("RegexExamples.java");
    }
    
    
    public static void findFileType(String filename){
        
        if(filename.endsWith(".txt")){
            System.out.println("Text file");
        } else if (filename.endsWith(".doc")){
            System.out.println("Document file");
        } else if (filename.endsWith(".xls")){
            System.out.println("Excel file");
        } else if (filename.endsWith(".java")){
            System.out.println("Java source file");
        } else {
            System.out.println("Other type of file");
        }
    }
    
    
}
