package org.java7recipes.chapter9;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/14/11
 * Time: 5:47 PM
 * Recipe for Assertions
 */
public class Recipe9_2 {
    public static void main (String[] args) {
        Recipe9_2 recipe = new Recipe9_2();
        recipe.start();
    }

    private void start() {

        assert (true) : "It didn't work";

        assert (false): "false!";

    }
}
