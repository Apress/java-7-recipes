package org.java7recipes.chapter6;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 10/1/11
 * Time: 4:21 PM
 * Recipe 6_2
 */
public class Recipe6_2 {
    Lock myLock = new ReentrantLock();

    public static void main(String[] args) {
        Recipe6_2 recipe = new Recipe6_2();
        recipe.start();
    }

    private void start() {
        for (int i = 0; i < 10; i++) {
            callFunctionThatHoldsLock();
        }
    }

    Random random = new Random();

    private void callFunctionThatHoldsLock() {
        myLock.lock();
        try {
            int number = random.nextInt(5);
            int result = 100 / number;
            System.out.println("A result is " + result);
            FileOutputStream file = new FileOutputStream("file.out");
            file.write(result);
            file.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            myLock.unlock();
        }
    }
}
