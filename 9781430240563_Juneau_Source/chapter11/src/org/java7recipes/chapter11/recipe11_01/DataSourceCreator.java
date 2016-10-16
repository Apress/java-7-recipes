
package org.java7recipes.chapter11.recipe11_01;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Recipe 11-1:  Example for creating a datasource
 * 
 * @author juneau
 */
public class DataSourceCreator {

    public void createDataSource() {
        org.java7recipes.chapter11.recipe11_01.FakeDataSourceDriver ds =
                new org.java7recipes.chapter11.recipe11_01.FakeDataSourceDriver();
        ds.setServerName("my-server");
        ds.setDatabaseName("JavaRecipes");
        ds.setDescription("Database connection for Java 7 Recipes");

    }

    public void registerDS() {
        try {
            Context ctx = new InitialContext();
            DataSource ds =
                    (DataSource) ctx.lookup("jdbc/java7recipesDB");
        } catch (NamingException ex) {
            ex.printStackTrace();
        }
    }
}
