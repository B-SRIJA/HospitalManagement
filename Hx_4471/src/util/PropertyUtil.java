package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
    private static final String PROPERTY_FILE_PATH = "database.properties";

    public static String getPropertyString() {
        try (InputStream input = PropertyUtil.class.getClassLoader().getResourceAsStream(PROPERTY_FILE_PATH)) {
            Properties properties = new Properties();
            if (input == null) {
                System.out.println("Sorry, unable to find " + PROPERTY_FILE_PATH);
                return null;
            }
            properties.load(input);

            String hostname = properties.getProperty("hostname");
            String dbname = properties.getProperty("dbname");
            String username = properties.getProperty("username");
            String password = properties.getProperty("password");
            String port = properties.getProperty("port");

            return "jdbc:mysql://" + hostname + ":" + port + "/" + dbname;
        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception appropriately
            return null;
        }
    }
}