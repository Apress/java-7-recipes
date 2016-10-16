
package org.java7recipes.chapter11.recipe11_14;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import org.java7recipes.chapter11.recipe11_01.CreateConnection;

/**
 * Recipe 11-14:  Example for populating a VARRAY
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
            storeArray();
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
    
    public static void storeArray() throws SQLException{
        PreparedStatement pstmt = null;
        String sql = null;
        Object [] chapters = {1,2,3};
        ARRAY chapterArray = null;
        try{
            ArrayDescriptor descriptor =
                    ArrayDescriptor.createDescriptor("CHAP_LIST_TYPE", conn);
            chapterArray = new ARRAY(descriptor, conn, chapters);
            sql = "INSERT INTO AUTHOR_RECIPES VALUES(" +
                  "author_recipes_seq.nextval, " +
                  "(select id from BOOK_AUTHOR where last = ?), " +
                  "?)";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "JUNEAU");
            pstmt.setArray(2, chapterArray);
            pstmt.executeUpdate();
        } catch (SQLException ex){
            ex.printStackTrace();
        } finally {
            if (pstmt != null){
                pstmt.close();
            }
        }
    }
}
