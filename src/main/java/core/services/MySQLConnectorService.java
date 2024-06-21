package core.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLConnectorService {

    private static final String PROPERTIES_FILE = "src/main/resources/app.properties";
    private static String url;
    private static String user;
    private static String password;

    static {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(PROPERTIES_FILE));
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
