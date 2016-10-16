package org.java7recipes.chapter8;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/8/11
 * Time: 9:10 PM
 * Safely Updating a List from multiple Threads
 */
public class Recipe8_4 {
    public static void main(String[] args) {
        Recipe8_4 recipe = new Recipe8_4();
        recipe.start();
    }

    private void start() {
        System.out.println("Using CopyOnWrite");
        copyOnWriteSolution();
        System.out.println("Using SynchronizedList");
        synchronizedListSolution();
    }

    private void synchronizedListSolution() {
        final List<String> list = Collections.synchronizedList(new ArrayList<String>());
        startUpdatingThread(list);
        synchronized (list) {
            for (String element : list) {
                System.out.println("Element :" + element);
            }
        }
        stopUpdatingThread();

    }

    private void copyOnWriteSolution() {
        CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<String>();
        startUpdatingThread(list);
        for (String element : list) {
            System.out.println("Element :" + element);
        }
        stopUpdatingThread();

    }

    private void stopUpdatingThread() {
        updatingThread.interrupt();

    }

    Random random = new Random();

    Thread updatingThread ;
    private void startUpdatingThread(final List<String> list) {
        updatingThread = new Thread(new Runnable() {
            long counter =0;
            public void run() {
                while (!Thread.interrupted()) {
                    int size = list.size();
                    if (random.nextBoolean()) {
                        if (size > 1) {
                            list.remove(random.nextInt(size - 1));
                        }
                    } else {
                        if (size < 20) {
                            list.add("Random string "+counter);
                        }
                    }
                    counter ++;
                }
            }

        }
        );
        updatingThread.start();

        // let it warm up for a second
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
