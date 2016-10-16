package org.java7recipes.chapter6;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/1/11
 * Time: 9:19 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe6_1 {
    public static void main(String[] args) {
        Recipe6_1 recipe = new Recipe6_1();
        recipe.start();
    }

    private void start() {
        System.out.println("Is th string 1234 longer than 5 chars?:"+isStringShorterThanFiveCharacters("1234"));
        System.out.println("Is th string 12345 longer than 5 chars?:"+isStringShorterThanFiveCharacters("12345"));
        System.out.println("Is th string 123456 longer than 5 chars?:"+isStringShorterThanFiveCharacters("123456"));
        System.out.println("Is th string null longer than 5 chars?:"+isStringShorterThanFiveCharacters(null));

    }

    private boolean isStringShorterThanFiveCharacters(String aString) {
        try {
            return aString.length() > 5;
        } catch (NullPointerException e) {
            System.out.println("An Exception Happened!");
            return false;
        }
    }
}
