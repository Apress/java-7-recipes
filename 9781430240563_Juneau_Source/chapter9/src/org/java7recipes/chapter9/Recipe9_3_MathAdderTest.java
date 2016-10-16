package org.java7recipes.chapter9;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 9/15/11
 * Time: 5:36 PM
 * Simple Unit Test
 */
public class Recipe9_3_MathAdderTest {

    @Test
    public void testAddBehavior() {
        Recipe_9_3_MathAdder adder = new Recipe_9_3_MathAdder();
        for (int i =0;i < 100;i++) {
            for (int j =0;j < 100;j++) {
                Assert.assertEquals(i+j,adder.addNumbers(i,j));

            }
        }
    }

    @Test
    public void testSubstractBehavior() {
        Recipe_9_3_MathAdder adder = new Recipe_9_3_MathAdder();
        for (int i =0;i < 100;i++) {
            for (int j =0;j < 100;j++) {
                Assert.assertEquals(i-j,adder.substractNumber(i,j));

            }
        }
    }
}
