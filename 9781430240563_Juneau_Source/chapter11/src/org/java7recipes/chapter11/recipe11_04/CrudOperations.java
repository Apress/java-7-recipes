
package org.java7recipes.chapter11.recipe11_04;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.java7recipes.chapter11.recipe11_01.CreateConnection;

/**
 * Recipe 11-4:  Performing CRUD Operations
 * 
 * @author juneau
 */
public class CrudOperations {
    public static Connection conn = null;

    public static void main(String[] args) {
        try {
            CreateConnection createConn = new CreateConnection();
            conn = createConn.getConnection();
            performCreate();
            performRead();
            performUpdate();
            performDelete();
            System.out.println("-- Final State --");
            performRead();
        } catch (java.sql.SQLException ex) {
            System.out.println(ex);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

    }
    
    private static void performCreate(){
        String sql = "INSERT INTO RECIPES VALUES(" +
                     "RECIPES_SEQ.NEXTVAL, " +
                     "'11-4', " + 
                     "'Performing CRUD Operations', " +
                     "'How to perform create, read, update, delete functions', " +
                     "'Recipe Text')";
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            // Returns row-count or 0 if not successful
            int result = stmt.executeUpdate(sql);
            if (result > 0){
                System.out.println("-- Record created --");
            } else {
                System.out.println("!! Record NOT Created !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            
        }

    }
    
    private static void performRead(){
        String qry = "select recipe_num, name, description from recipes";
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(qry);
            while (rs.next()) {
                String recipe = rs.getString("RECIPE_NUM");
                String name = rs.getString("NAME");
                String desc = rs.getString("DESCRIPTION");

                System.out.println(recipe + "\t" + name + "\t" + desc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            
        }
        
    }
    
    private static void performUpdate(){
        String sql = "UPDATE RECIPES " +
                     "SET RECIPE_NUM = '11-5' " +
                     "WHERE RECIPE_NUM = '11-4'";
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);
            if (result > 0){
                System.out.println("-- Record Updated --");
            } else {
                System.out.println("!! Record NOT Updated !!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    }
    
    private static void performDelete(){
        String sql = "DELETE FROM RECIPES WHERE RECIPE_NUM = '11-5'";
        Statement stmt = null;

        try {
            stmt = conn.createStatement();
            int result = stmt.executeUpdate(sql);
            if (result > 0){
                System.out.println("-- Record Deleted --");
            } else {
                System.out.println("!! Record NOT Deleted!!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
}
