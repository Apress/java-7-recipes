package org.java7recipes.chapter8;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/9/11
 * Time: 8:32 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe8_6 {
    public static void main (String [] args) throws InterruptedException {
        Recipe8_6 recipe = new Recipe8_6();
        recipe.start();
    }

    private void start() throws InterruptedException {
        BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
        for (int i =0;i < 10;i++) {
            final int localI = i;
            queue.add(new Runnable() {
                public void run() {
                    doExpensiveOperation(localI);
                }
            });
        }
        ThreadPoolExecutor executor = new ThreadPoolExecutor(10,10,1000, TimeUnit.MILLISECONDS, queue);
        executor.prestartAllCoreThreads();
        executor.shutdown();
        executor.awaitTermination(100000,TimeUnit.SECONDS);

        System.out.println("Look ma! all operations were completed");
    }

    private void doExpensiveOperation(int index) {
        System.out.println("Starting expensive operation "+index);
        try {
            Thread.sleep(index * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Ending   expensive operation " + index);
    }

}
