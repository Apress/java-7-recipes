package org.java7recipes.chapter6;

import java.util.Random;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/31/11
 * Time: 5:53 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe6_5 {
    public static void main(String[] args) {
        Recipe6_5 recipe = new Recipe6_5();
        recipe.start();
        recipe.startForCurrentThread();
    }

    private void start() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("Woa! there was an exception thrown somewhere! "+t.getName()+": "+e);
            }
        });

        final Random random = new Random();
        for (int j = 0; j < 10; j++) {
            int divisor = random.nextInt(4);
            System.out.println("200 / " + divisor + " Is " + (200 / divisor));
        }
    }

    private void startForCurrentThread() {
        Thread.currentThread().setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                System.out.println("In this thread "+t.getName()+" an exception was thrown "+e);
            }
        });

        Thread someThread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(200/0);
            }
        });
        someThread.setName("Some Unlucky Thread");
        someThread.start();

        System.out.println("In the main thread "+ (200/0));
    }
}
