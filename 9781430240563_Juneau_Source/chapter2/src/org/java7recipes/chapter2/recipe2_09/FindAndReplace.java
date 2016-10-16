
package org.java7recipes.chapter2.recipe2_09;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Recipe 2-9
 * 
 * Replacing All Text Matches
 * 
 * @author juneau
 */
public class FindAndReplace {
    
    public static void main(String[] args) {
        findAndReplaceWithPatterns();
    }
    
    
    public static void findAndReplaceWithPatterns() {

        String str = "I love Java 7!  It is my favorite language.  Java 7 is the "
                + "7th version of this great programming language.";

        boolean result = false;

        Pattern pattern = Pattern.compile("[0-7]");
        Matcher matcher = pattern.matcher(str);

        System.out.println("Original: " + str);

        System.out.println("Replacement: " + matcher.replaceAll("6"));



    }
    
    
}
