package by.lev.repository;

import by.lev.connection_db.AppConnection;
import by.lev.domain.User;
import by.lev.metainf.SQLScript;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.lev.metainf.SQLScript.USER_REPOSITORY_CREATE;
import static by.lev.metainf.SQLScript.USER_REPOSITORY_DELETE;
import static by.lev.metainf.SQLScript.USER_REPOSITORY_DELETE_BY_NAME;
import static by.lev.metainf.SQLScript.USER_REPOSITORY_READ;
import static by.lev.metainf.SQLScript.USER_REPOSITORY_READ_ALL;

public class UserRepository implements UserRepositoryInterface {

    Connection connection;


    @Override
    public void create(User user) throws SQLException {
        connection = new AppConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(USER_REPOSITORY_CREATE.getValue());
        preparedStatement.setInt(1, user.getId());
        preparedStatement.setString(2, user.getName());
        preparedStatement.setString(3, user.getAddress());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public User read(Integer userId) throws SQLException {
        connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                USER_REPOSITORY_READ.getValue() + userId);
        User user = User.builder()
                .id(rs.getInt("userId"))
                .name(rs.getString("name"))
                .address(rs.getString("address"))
                .build();
        statement.close();
        connection.close();
        return user;
    }

    @Override
    public List<User> readAll() throws SQLException {
        connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(USER_REPOSITORY_READ_ALL.getValue());
        List<User> users = new ArrayList<>();
        while (rs.next()) {
            User user = User.builder()
                    .id(rs.getInt("userId"))
                    .name(rs.getString("name"))
                    .address(rs.getString("address"))
                    .build();
            users.add(user);
        }
        statement.close();
        connection.close();
        return users;
    }

    @Override
    public void update(User user, Integer userId) throws SQLException {
        connection = new AppConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                SQLScript.USER_REPOSITORY_UPDATE.getValue());
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getAddress());
        preparedStatement.setInt(3, userId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();

    }

    @Override
    public void delete(Integer userId) throws SQLException {
        connection = new AppConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                USER_REPOSITORY_DELETE.getValue());
        preparedStatement.setInt(1, userId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }


    @Override
    public void delete(String name) throws SQLException {
        connection = new AppConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(
                USER_REPOSITORY_DELETE_BY_NAME.getValue());
        preparedStatement.setString(1, name);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
