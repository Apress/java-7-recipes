package org.java7recipes.chapter8;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/8/11
 * Time: 9:08 PM
 * Recipe 8_1
 */
public class Recipe8_1 {
    public static void main(String[] args) {
        Recipe8_1 recipe = new Recipe8_1();
        recipe.start();
    }

    private void start() {
        Thread backgroundThread = new Thread(new Runnable() {
            public void run() {
                doSomethingInBackground();
            }
        }, "Background Thread");

        System.out.println("Start");
        backgroundThread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": is counting " + i);
        }

        System.out.println("Done");
    }

    private void doSomethingInBackground() {
        System.out.println(Thread.currentThread().getName() + ": is Running in the background");
    }
}
