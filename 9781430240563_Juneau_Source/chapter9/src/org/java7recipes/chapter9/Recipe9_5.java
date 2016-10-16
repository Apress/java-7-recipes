package org.java7recipes.chapter9;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/18/11
 * Time: 9:17 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe9_5 {
    private String testString;

    public String getTestString() {
        return testString;
    }

    public void setTestString(String testString) {
        this.testString = testString;
    }
    public static void main(String[] args) {
        Recipe9_5 recipe = new Recipe9_5();
        recipe.setTestString("A string");
        System.out.println("is string 'A string?':"+recipe.testforAString());
    }

    private boolean testforAString() {
        return (testString == "A string");
    }
}
