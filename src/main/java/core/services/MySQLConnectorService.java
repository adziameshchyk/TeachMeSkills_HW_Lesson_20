package core.services;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnectorService {

    private static final String DATABASE_CONNECTIVITY_PROPERTY_FILE_PATH = "src/main/resources/app.properties";

    private static Properties properties;
    private static Connection connection;

    static {
        init();
    }

    private static void init() {
        try {
            properties = loadProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            return connection;
        }

        try {
            return connection = DriverManager.getConnection(
                    properties.getProperty("db.url"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password")
            );
        } catch (SQLException e) {
            System.out.println("Unable to get connection to MySQL schema!");
            e.printStackTrace();
        }

        return null;
    }

    private static Properties loadProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(Files.newInputStream(Paths.get(DATABASE_CONNECTIVITY_PROPERTY_FILE_PATH)));

        return properties;
    }
}
