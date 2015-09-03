package util;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private Connection conn = null;

    public void setConnNull() {
        conn=null;
    }

    public DBConnection() {
        conn = getConnection();
    }

    public Connection getConnection() {
        if (conn != null) {
            return conn;
        } else {
            try {
                InputStream inputStream = DBConnection.class.getClassLoader().getResourceAsStream("amazondb.properties");
                Properties properties = new Properties();
                properties.load(inputStream);

                String dbDriver = properties.getProperty("dbDriver");
                String connectionUrl = properties.getProperty("connectionUrl");
                String userName = properties.getProperty("userName");
                String password = properties.getProperty("password");

                Class.forName(dbDriver).newInstance();
                conn = DriverManager.getConnection(connectionUrl, userName, password);

            } catch (Exception e) {
                e.printStackTrace();
            }
            return conn;
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
