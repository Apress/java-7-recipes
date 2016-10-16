package org.java7recipes.chapter14.recipe14_07;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

/**
 * DBUtils class is responsible for saving contact information
 * into a database.
 * requires jdk7
 * @author cdea
 */

public class DBUtils {

    private static String framework = "embedded";
    private static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static String protocol = "jdbc:derby:";

    public static int saveContact(String fName, String lName) {
        int pk = (fName + lName).hashCode();

        loadDriver();

        Connection conn = null;
        ArrayList statements = new ArrayList(); 
        PreparedStatement psInsert = null;
        Statement s = null;
        ResultSet rs = null;
        try {
            // connection properties
            Properties props = new Properties(); 
            props.put("user", "scott");
            props.put("password", "tiger");

            // database name
            String dbName = "demoDB"; 

            conn = DriverManager.getConnection(protocol + dbName
                    + ";create=true", props);

            System.out.println("Creating database " + dbName);

            // handle transaction
            conn.setAutoCommit(false);

            s = conn.createStatement();
            statements.add(s);

//            s.execute("drop table contact");  

            // Create a contact table...
            s.execute("create table contact(id int, fName varchar(40), lName varchar(40))");
            System.out.println("Created table contact");


            psInsert = conn.prepareStatement("insert into contact values (?, ?, ?)");
            statements.add(psInsert);
            psInsert.setInt(1, pk);
            psInsert.setString(2, fName);
            psInsert.setString(3, lName);
            psInsert.executeUpdate();
            conn.commit();
            System.out.println("Inserted " + fName + " " + lName);

            // delete the table for demo
            s.execute("drop table contact");
            System.out.println("Dropped table contact");

            conn.commit();
            System.out.println("Committed the transaction");

            // standard checking code when shutting down database.
            // code from http://db.apache.org/derby/
            if (framework.equals("embedded")) {
                try {
                    // shuts down Derby
                    DriverManager.getConnection("jdbc:derby:;shutdown=true");

                } catch (SQLException se) {
                    if (((se.getErrorCode() == 50000)
                            && ("XJ015".equals(se.getSQLState())))) {
                        System.out.println("Derby shut down normally");
                    } else {
                        System.err.println("Derby did not shut down normally");
                        se.printStackTrace();
                    }
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } finally {
            close(rs);

            int i = 0;
            while (!statements.isEmpty()) {
                Statement st = (Statement) statements.remove(i);
                close(st);
            }

            close(conn);

        }

        return pk;
    }

    private static void close(AutoCloseable closable) {
        try {
            if (closable != null) {
                closable.close();
                closable = null;
            }
        } catch (Exception sqle) {
            sqle.printStackTrace();
        }
    }

    private static void loadDriver() {
        
            try {
                Class.forName(driver).newInstance();
                System.out.println("Loaded driver");
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        
    }
}