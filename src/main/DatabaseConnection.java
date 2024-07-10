package main;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    public static Connection getConnection() throws SQLException {
        Properties props = new Properties();
      try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/config.properties")) {
            props.load(fis);
        } catch (IOException e) {
            throw new SQLException("Error loading config file", e);
        }

        String database = props.getProperty("MYSQL_DATABASE");
        String host = props.getProperty("MYSQLHOST");
        String password = props.getProperty("MYSQLPASSWORD");
        String port = props.getProperty("MYSQLPORT");
        String user = props.getProperty("MYSQLUSER");

        System.getenv().forEach((k, v) -> System.out.println(k + ":" + v));

         if (database == null || host == null || password == null || port == null || user == null) {
            throw new SQLException("Missing environment variables for database connection");
        }

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false&serverTimezone=UTC";

        return DriverManager.getConnection(url, user, password);
    }
}
