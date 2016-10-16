package org.java7.recipes.chapter6;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/31/11
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe6_10 {
    public static void main (String[] args) {
        Recipe6_10 recipe = new Recipe6_10();
        recipe.start();
    }

    private void start() {
        System.out.println(System.getProperty("user.dir"));
        PropertyConfigurator.configure("logging.conf");
        Logger logger = LoggerFactory.getLogger("superLogger");
        logger.info("Logging for the first Time!");
        logger.warn("A warning to be had");
        logger.error("This is an error!");


        Logger rollingLogger = LoggerFactory.getLogger("rollingLogger");
        for (int i =0;i < 10000;i++) {
            rollingLogger.info("Logging for an event with :"+i);
        }
    }
}
