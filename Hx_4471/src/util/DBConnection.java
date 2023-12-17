package util;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;
    public static Connection getConnection() {
    	try {
            // Check if a connection has not been established or has been closed
            if (connection == null || connection.isClosed()) {
                // Load the JDBC driver
                Class.forName("com.mysql.jdbc.Driver");

                // Replace the placeholder values with your actual database details
                String url = "jdbc:mysql://localhost:3306/hexa";
                String username = "root";
                String password = "root";

                // Create a new connection
                connection = DriverManager.getConnection(url, username, password);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(); // Handle exceptions appropriately
        }

        return connection;
    }
    }

