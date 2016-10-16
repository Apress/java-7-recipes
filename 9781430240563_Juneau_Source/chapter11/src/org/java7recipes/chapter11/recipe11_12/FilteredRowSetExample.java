package org.java7recipes.chapter11.recipe11_12;

import com.sun.rowset.FilteredRowSetImpl;
import com.sun.rowset.JoinRowSetImpl;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JoinRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import org.java7recipes.chapter11.recipe11_01.CreateConnection;

/**
 * Recipe 11-12:  Example of a FilteredRowSet.
 * 
 * @author juneau
 */
public class FilteredRowSetExample {

    public static Connection conn = null;
    public static CreateConnection createConn;
    public static FilteredRowSet frs = null;

    public static void main(String[] args) {
        boolean successFlag = false;
        try {
            createConn = new CreateConnection();
            conn = createConn.getConnection();
            implementFilteredRowSet();
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
            if (frs != null) {
                try {
                    frs.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }

    public static void implementFilteredRowSet() {

        String[] authorArray = {"DEA", "JUNEAU"};

        AuthorFilter authorFilter = new AuthorFilter(authorArray, 3);

        try {
            frs = new FilteredRowSetImpl();

            frs.setCommand("SELECT CHAPTER_NUMBER, CHAPTER_TITLE, LAST "
                    + "FROM BOOK_AUTHOR BA, "
                    + "     AUTHOR_WORK AW "
                    + "WHERE AW.AUTHOR_ID = BA.ID");

            frs.execute(conn);

            System.out.println("Prior to adding filter:");
            viewRowSet(frs);
            System.out.println("Adding author filter:");
            frs.beforeFirst();
            frs.setFilter(authorFilter);
            viewRowSet(frs);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void viewRowSet(RowSet rs) {
        try {
            while (rs.next()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " - "
                        + rs.getString(3));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
