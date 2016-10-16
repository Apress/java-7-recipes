package org.java7.recipes.chapter6;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by IntelliJ IDEA.
 * User: Freddy
 * Date: 8/31/11
 * Time: 9:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Recipe6_11 {
    public static void main (String [] args) {
        Recipe6_11 recipe = new Recipe6_11();
        recipe.start();
    }

    static Logger applicationLogger = LoggerFactory.getLogger("rollingLogger");
    private void start() {
        PropertyConfigurator.configure("logging.conf");
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                applicationLogger.error("Error in thread "+t+" caused by ",e);
            }
        });

        int c = 20/0;

    }

    private String exceptionToString(Throwable e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();

    }
}
