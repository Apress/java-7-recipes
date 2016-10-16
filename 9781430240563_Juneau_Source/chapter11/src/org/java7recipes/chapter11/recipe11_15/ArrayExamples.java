
package org.java7recipes.chapter11.recipe11_15;

import java.math.BigDecimal;
import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.java7recipes.chapter11.recipe11_01.CreateConnection;

/**
 * Recipe 11-16:  Example for populating a VARRAY
 * 
 * @author juneau
 */
public class ArrayExamples {
   public static Connection conn = null;
    public static CreateConnection createConn;

    public static void main(String[] args) {
        boolean successFlag = false;
        try {
            createConn = new CreateConnection();
            conn = createConn.getConnection();
            queryArray();
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
    
    public static void queryArray(){
        PreparedStatement pstmt = null;
        String sql = null;
        ResultSet rset = null;
        Array chapters = null;
        try{

            sql = "SELECT AUTHOR_ID, CHAPTER_LIST, LAST " +
                  "FROM AUTHOR_RECIPES AR, " +
                  " BOOK_AUTHOR BA " +
                  "WHERE AR.AUTHOR_ID = BA.ID";
            pstmt = conn.prepareStatement(sql);

            rset = pstmt.executeQuery(sql);
            while(rset.next()){
                chapters = rset.getArray(2);
                BigDecimal[] chapterNumbers = (BigDecimal[]) chapters.getArray();
                System.out.println(rset.getString(3) + " Chapters \n"); 
                for (BigDecimal idx:chapterNumbers){
                    System.out.println(idx + "\n");
                }
            }

        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            if (pstmt != null){
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (rset != null){
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
