package org.java7recipes.chapter6;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/31/11
 * Time: 5:36 PM
 *
 */
public class Recipe6_3 {
    public static void main(String[] args) {
        Recipe6_3 recipe = new Recipe6_3();
        recipe.start();
    }

    private void start() {
        try {
            callSomeFunctionThatMightThrow(null);
        } catch (NullPointerException e) {
            System.out.println("There was an null parameter!");
        }

    }

    private void callSomeFunctionThatMightThrow(Object o) {
        if (o == null) throw new NullPointerException("The object is null");

    }
}
