package org.java7recipes.chapter6;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.logging.LogManager;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/31/11
 * Time: 9:54 PM
 * Loggging Exceptions
 */
public class Recipe6_11 {
    public static void main (String [] args) {
        Recipe6_11 recipe = new Recipe6_11();
        recipe.start();
    }

    static Logger rootLogger = LoggerFactory.getLogger("");
    private void start() {
        loadLoggingConfiguration();
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                rootLogger.error("Error in thread "+t+" caused by ",e);
            }
        });

        int c = 20/0;
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
