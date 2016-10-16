package org.java7recipes.chapter9;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/15/11
 * Time: 5:18 PM
 * Exception
 */
public class Recipe9_1 {
    public static void main (String[] args) {
        Recipe9_1 recipe = new Recipe9_1();
        recipe.start();
    }

    private void start() {
        try {
            int a = 5/0;
        }  catch (Exception e) {
            e.printStackTrace();
        }

    }
}
