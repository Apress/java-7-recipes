package org.java7recipes.chapter6;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/31/11
 * Time: 6:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe6_6 {
    public static void main(String []args) {
        Recipe6_6 recipe = new Recipe6_6();
        recipe.start();
    }

    private void start() {
        try (
                FileOutputStream fos = new FileOutputStream("out.log");
                BufferedOutputStream bos = new BufferedOutputStream(fos);
                DataOutputStream dos = new DataOutputStream(bos)
        ) {
            dos.writeUTF("This is being written");
            // let's throw an exception and

            dos.close();
        } catch (Exception e) {
            System.out.println("Some bad exception happened ");
        }
    }
}
