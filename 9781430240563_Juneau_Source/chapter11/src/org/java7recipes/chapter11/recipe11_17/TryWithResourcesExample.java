package org.java7recipes.chapter11.recipe11_17;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.java7recipes.chapter11.recipe11_01.CreateConnection;

/**
 * Recipe 11-17:  Using try-with-resources
 * 
 * @author juneau
 */
public class TryWithResourcesExample {

    public static CreateConnection createConn;

    public static void main(String[] args) {

        createConn = new CreateConnection();

        queryDatabase();


    }

    public static void queryDatabase() {
        String qry = "select recipe_num, name, description from recipes";


        try (Connection conn = createConn.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(qry);) {

            while (rs.next()) {
                String recipe = rs.getString("RECIPE_NUM");
                String name = rs.getString("NAME");
                String desc = rs.getString("DESCRIPTION");

                System.out.println(recipe + "\t" + name + "\t" + desc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
