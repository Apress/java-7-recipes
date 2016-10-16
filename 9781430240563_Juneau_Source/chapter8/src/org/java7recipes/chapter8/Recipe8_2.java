package org.java7recipes.chapter8;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/8/11
 * Time: 10:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe8_2 {
    public static void main(String[] args) {
        Recipe8_2 recipe = new Recipe8_2();
        recipe.start();
    }
    Set<Thread> updateThreads = new HashSet<Thread>();

    private void start() {
        ConcurrentMap<Integer,String> concurrentMap = new ConcurrentHashMap<Integer, String>();
        for (int i =0;i < 1000;i++) {
            startUpdateThread(i, concurrentMap);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        for (Map.Entry<Integer, String> entry : concurrentMap.entrySet()) {
            System.out.println("Key :"+entry.getKey()+" Value:"+entry.getValue());
        }

        for (Thread thread : updateThreads) {
            thread.interrupt();
        }
        // goodnight!
    }

    Random random = new Random();
    private void startUpdateThread(int i, final ConcurrentMap<Integer, String> concurrentMap) {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                while (!Thread.interrupted()) {
                    int randomInt = random.nextInt(20);
                    concurrentMap.put(randomInt, UUID.randomUUID().toString());
                }
            }
        });
        thread.setName("Update Thread "+i);
        updateThreads.add(thread);
        thread.start();
    }
}
