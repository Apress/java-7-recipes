package org.java7recipes.chapter6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.LogManager;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/31/11
 * Time: 9:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe6_9 {
    public static void main (String[] args) {
        Recipe6_9 recipe = new Recipe6_9();
        recipe.start();
    }

    private void start() {
        loadLoggingConfiguration();
        Logger logger = LoggerFactory.getLogger("recipeLogger");
        logger.info("Logging for the first Time!");
        logger.warn("A warning to be had");
        logger.error("This is an error!");
    }

    private void loadLoggingConfiguration() {
        FileInputStream ins = null;
        try {
            ins = new FileInputStream(new File("logging.properties"));
            LogManager.getLogManager().readConfiguration(ins);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
