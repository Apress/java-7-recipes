
package org.java7recipes.chapter11.recipe11_09;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.java7recipes.chapter11.recipe11_01.CreateConnection;

/**
 * Recipe 11-9:  Example of using an Updatable ResultSet
 * 
 * @author juneau
 */
public class UpdateResultSetExample {
    public static Connection conn = null;

    public static void main(String[] args) {
        boolean successFlag = false;
        try {
            CreateConnection createConn = new CreateConnection();
            conn = createConn.getConnection();
            
            // Perform Initial Query
            queryDbRecipes();
            
            // Update Resultset Row
            queryAndUpdateDbRecipes("11-1");
                        
            // Query to see final results
            queryDbRecipes();
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
    
    private static void queryAndUpdateDbRecipes(String recipeNumber){
        String sql = "SELECT ID, RECIPE_NUM, NAME, DESCRIPTION " +
                     "FROM RECIPES " +
                     "WHERE RECIPE_NUM = ?";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            
            pstmt.setString(1, recipeNumber);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                String desc = rs.getString(4);
                System.out.println("Updating row" + desc);
                
                rs.updateString(4, desc + " -- More to come");
                rs.updateRow();
            }           
            
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    }
    
        private static void queryDbRecipes(){
        String sql = "SELECT ID, RECIPE_NUM, NAME, DESCRIPTION " +
                     "FROM RECIPES";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()){
                System.out.println(rs.getString(2) + ": " + rs.getString(3) + 
                                " - " + rs.getString(4));
            }           
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
        
    }

}
