package org.java7recipes.chapter9;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/18/11
 * Time: 10:34 PM
 * Typical deadlock situation
 */
public class Recipe9_8 {
    Lock firstLock = new ReentrantLock();
    Lock secondLock = new ReentrantLock();


    public static void main (String[] args) {
        Recipe9_8 recipe = new Recipe9_8();
        recipe.start();
    }

    private void start() {
        firstLock.lock();
        Thread secondThread = new Thread(new Runnable() {
            public void run() {
                secondLock.lock();
                firstLock.lock();
            }
        });

        secondThread.start();
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        secondLock.lock();

        secondLock.unlock();
        firstLock.unlock();

    }

}
