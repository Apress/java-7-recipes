package org.java7recipes.chapter9;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/18/11
 * Time: 9:41 PM
 * Recipe 9_7
 */
public class Recipe9_6 {
    Map<String, Integer> leakyMap = new HashMap<String, Integer>();
    public static void main(String[] args) {
        Recipe9_6 recipe = new Recipe9_6();
        recipe.start();
    }

    private void start() {

        for (int i= 0;i < 5000;i++) {
            leakyMap.put(UUID.randomUUID().toString(),i);
                for (int j = 0;j < 10000;j++ ) {
                    String stringThatConsumes = new String("a"+i);
                }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (i % 20 == 0) System.out.println("Writing :"+i);
        }
    }
}
