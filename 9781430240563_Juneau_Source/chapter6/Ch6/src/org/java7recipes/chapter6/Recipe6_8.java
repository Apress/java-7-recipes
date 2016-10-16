package org.java7recipes.chapter6;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/31/11
 * Time: 9:00 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe6_8 {
    public static void main (String[] args) {
        Recipe6_8 recipe = new Recipe6_8();
        recipe.start();
    }

    private void start() {
        try {
            doSomeWork();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void doSomeWork() throws IOException, InterruptedException {
        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<String>();

        try {
            FileOutputStream fos = new FileOutputStream("out.log");
            DataOutputStream dos = new DataOutputStream(fos);
            while (!queue.isEmpty()) {
                dos.writeUTF(queue.take());
            }
        } catch (InterruptedException | IOException e ) {
            e.printStackTrace();
            throw e;
        }

    }
}
