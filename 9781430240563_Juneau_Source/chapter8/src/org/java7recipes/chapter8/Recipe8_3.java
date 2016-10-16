package org.java7recipes.chapter8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/8/11
 * Time: 10:24 PM
 * Safely inserting a key into a Map
 */
public class Recipe8_3 {

    Set<Thread> updateThreads = new HashSet<Thread>();

    public static void main(String[] args) {
        Recipe8_3 recipe = new Recipe8_3();
        recipe.start();
    }

    private void start() {
        ConcurrentMap<Integer, String> concurrentMap = new ConcurrentHashMap<Integer, String>();
        for (int i = 0; i < 100; i++) {
            startUpdateThread(i, concurrentMap);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (Map.Entry<Integer, String> entry : concurrentMap.entrySet()) {
            System.out.println("Key :" + entry.getKey() + " Value:" + entry.getValue());
        }

    }

    Random random = new Random();

    private void startUpdateThread(final int i, final ConcurrentMap<Integer, String> concurrentMap) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                int randomInt = random.nextInt(20);
                String previousEntry = concurrentMap.putIfAbsent(randomInt, "Thread # " + i + " has made it!");
                if (previousEntry != null) {
                    System.out.println("Thread # " + i + " tried to update it but guess what, we're too late!");
                    return;
                } else {
                    System.out.println("Thread # " + i + " has made it!");
                    return;

                }
            }
        });
        thread.start();
    }

}
