package service;

import utils.properties.PropertiesHolder;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionService {

    public static Connection createConnection() {
        Connection connection = null;

        try {
//            Class.forName("org.sqlite.JDBC");
            Class.forName("com.mysql.cj.jdbc.Driver");
//            connection = DriverManager.getConnection(PropertiesHolder.getProperty("DB_URL"));
            connection = DriverManager.getConnection(PropertiesHolder.getProperty("DB_URL"), 
            		PropertiesHolder.getProperty("DB_USER"), 
            		PropertiesHolder.getProperty("DB_PASSW"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}
