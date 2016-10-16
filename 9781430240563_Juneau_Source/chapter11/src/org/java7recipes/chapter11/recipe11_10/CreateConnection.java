
package org.java7recipes.chapter11.recipe11_10;

import org.java7recipes.chapter11.recipe11_1.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author juneau
 */
public class CreateConnection {
    
    static Properties props = new Properties();
    
    
    private String hostname = null;
    private String port = null;
    private String database = null;
    private String username = null;
    private String password = null;
    private String jndi = null;
    private String jdbcUrl = null;
    
    public CreateConnection(){
        InputStream in = null;
        try {
            // Looks for properties file in the root of the src directory in Netbeans project
            in = Files.newInputStream(FileSystems.getDefault().getPath(System.getProperty("user.dir") + File.separator + "db_props.properties"));
            props.load(in);
            in.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        loadProperties();
      
    }
    
    public void loadProperties(){
        setHostname(props.getProperty("host_name"));
        setPort(props.getProperty("port_number"));
        setDatabase(props.getProperty("db_name"));
        setUsername(props.getProperty("username"));
        setPassword(props.getProperty("password"));
        setJndi(props.getProperty("jndi"));
        jdbcUrl = "jdbc:oracle:thin:@" + this.getHostname() + ":" + 
                        this.getPort()  + ":" + this.getDatabase();
        
    }
    
    /**
     * Demonstrates obtaining a connection via DriverManager
     * @return
     * @throws SQLException 
     */
    public Connection getConnection() throws SQLException {
        Connection conn = null;

        String jdbcUrl = "jdbc:oracle:thin:@" + this.getHostname() + ":" + 
                        this.getPort()  + ":" + this.getDatabase();
        conn = DriverManager.getConnection(jdbcUrl, getUsername(), getPassword());
        System.out.println("Successfully connected");
        return conn;
    }
    
    /**
     * Demonstrates obtaining a connection via a DataSource object
     * @return 
     */
    public Connection getDSConnection() {
        Connection conn = null;
        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup(this.getJndi());
            conn = ds.getConnection();

        } catch (NamingException | SQLException ex) {
            ex.printStackTrace();
        }
        return conn;
    }

    /**
     * @return the hostname
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(String port) {
        this.port = port;
    }

    /**
     * @return the database
     */
    public String getDatabase() {
        return database;
    }

    /**
     * @param database the database to set
     */
    public void setDatabase(String database) {
        this.database = database;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the jndi
     */
    public String getJndi() {
        return jndi;
    }

    /**
     * @param jndi the jndi to set
     */
    public void setJndi(String jndi) {
        this.jndi = jndi;
    }

    /**
     * @return the jdbcUrl
     */
    public String getJdbcUrl() {
        return jdbcUrl;
    }

    /**
     * @param jdbcUrl the jdbcUrl to set
     */
    public void setJdbcUrl(String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
    }
}
