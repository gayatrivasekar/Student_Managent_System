package com.studentmanagement.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Utility class to manage database connection for Student Management System.
 * Follows Singleton Pattern to reuse a single connection instance.
 *
 * DB Used: MySQL
 * 
 * JavaBean-compatible
 */
public class StudentDatabase {
	
    /**
     * Singleton Connection object
     */
    private static Connection connection = null;

    /**
     * Private constructor to prevent instantiation
     */
    private StudentDatabase() {
    }

    /**
     * Establishes and returns a single reusable JDBC connection.
     * 
     * @return Connection object to MySQL database
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try { 
                /**
                 * Load MYSQL JDBC driver.
                 */
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException("MySQL JDBC Driver not found.", e);
            }


            /**
             * Load properties from external file
             */
            Properties props = new Properties();
            try (FileInputStream fis = new FileInputStream("config.properties")) {
                props.load(fis);
            } catch (IOException e) {
                throw new SQLException("Unable to read config.properties file.", e);
            }

            /**
             * Extract JDBC URL from properties
             */
           
            String url = props.getProperty("db.url");
            

            /**
             * Use the properties object directly in getConnection
             */    
            connection = DriverManager.getConnection(url, props);
        }
        return connection;
    }
}
