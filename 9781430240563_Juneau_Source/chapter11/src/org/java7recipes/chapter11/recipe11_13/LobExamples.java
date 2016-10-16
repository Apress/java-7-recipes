package org.java7recipes.chapter11.recipe11_13;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;

import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.java7recipes.chapter11.recipe11_01.CreateConnection;

/**
 * Recipe 11-13:  Example of using LOB
 * 
 * @author juneau
 */
public class LobExamples {

    public static Connection conn = null;
    public static CreateConnection createConn;

    public static void main(String[] args) {
        boolean successFlag = false;
        try {
            createConn = new CreateConnection();
            conn = createConn.getConnection();
            loadClob();
            readClob();
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

    public static void loadClob() {
        PreparedStatement pstmt = null;
        String sql = null;
        Clob textClob = null;
        try {
            textClob = conn.createClob();
            textClob.setString(1, "This will be the recipe text in clob format");
            sql = "INSERT INTO RECIPE_TEXT VALUES("
                    + "?, "
                    + "(select id from recipes where recipe_num = '11-1'), "
                    + "?)";
            pstmt = conn.prepareStatement(sql);

            // obtain the sequence number in real world
            pstmt.setInt(1, 1);
            // set the clob value
            pstmt.setClob(2, textClob);
            pstmt.executeUpdate();


        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (textClob != null) {
                try {
                    textClob.free();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void readClob() {
        PreparedStatement pstmt = null;
        String qry = null;
        Clob theClob = null;
        ResultSet rs = null;
        try {
            qry = "select text from recipe_text";
            pstmt = conn.prepareStatement(qry);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                theClob = rs.getClob(1);
                System.out.println("Clob length: " + theClob.length());
                System.out.println(theClob.toString());
            }
            System.out.println(theClob.toString());
            java.io.InputStream in =
                    theClob.getAsciiStream();
            int i;
            while ((i = in.read()) > -1) {
                System.out.print((char) i);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {

            ex.printStackTrace();
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
