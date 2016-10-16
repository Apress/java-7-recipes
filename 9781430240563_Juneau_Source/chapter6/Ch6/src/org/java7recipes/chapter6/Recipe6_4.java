package org.java7recipes.chapter6;


import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/31/11
 * Time: 5:40 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe6_4 {
    public static void main(String[] args) {
        Recipe6_4 recipe = new Recipe6_4();
        recipe.start();
        recipe.startClassic();
    }

    private void startClassic() {
        try {
            Class<?> stringClass = Class.forName("java.lang.String");
            FileInputStream in = new FileInputStream("myFile.log") ; // Can throw IOException
            in.read();


        } catch (IOException e) {
            System.out.println("There was an IOException "+e);
        } catch (ClassNotFoundException e) {

            System.out.println("There was a CLassCastException "+e);

        }
    }

    private void start() {

        try {
            Class<?> stringClass = Class.forName("java.lang.String");
            FileInputStream in = new FileInputStream("myFile.log") ; // Can throw IOException
            in.read();


        } catch (IOException | ClassNotFoundException e) {
            System.out.println("An exception of type "+e.getClass()+" was thrown! "+e);
        }
    }
}
