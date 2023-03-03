package by.lev;

import by.lev.connection_db.AppConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Test {
    public static void main(String[] args) throws Exception {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "select seq from sqlite_sequence where name='Transactions';"
        );
        int latest = rs.getInt("seq");
        System.out.println(latest);

    }
}
