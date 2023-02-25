package by.lev.connection_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AppConnection {

    public Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:c:\\sqlitedb\\sqlitedb.db");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
}
