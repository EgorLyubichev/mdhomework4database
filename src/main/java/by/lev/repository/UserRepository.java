package by.lev.repository;

import by.lev.app_exception.AppException;
import by.lev.connection_db.AppConnection;
import by.lev.domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.lev.app_exception.AppError.USER_REPOSITORY_ERROR_01;
import static by.lev.app_exception.AppError.USER_REPOSITORY_ERROR_02;
import static by.lev.app_exception.AppError.USER_REPOSITORY_ERROR_03;
import static by.lev.app_exception.AppError.USER_REPOSITORY_ERROR_04;
import static by.lev.app_exception.AppError.USER_REPOSITORY_ERROR_05;
import static by.lev.app_exception.AppError.USER_REPOSITORY_ERROR_06;
import static by.lev.app_exception.AppError.USER_REPOSITORY_ERROR_07;
import static by.lev.metainf.SQLScript.UR_CREATE;
import static by.lev.metainf.SQLScript.UR_DELETE;
import static by.lev.metainf.SQLScript.UR_DELETE_BY_NAME;
import static by.lev.metainf.SQLScript.UR_READ;
import static by.lev.metainf.SQLScript.UR_READ_ALL;
import static by.lev.metainf.SQLScript.UR_READ_BY_NAME;
import static by.lev.metainf.SQLScript.UR_UPDATE;

public class UserRepository implements UserRepositoryInterface {

    Connection connection;

    @Override
    public void create(User user) throws AppException {
        try {
            connection = new AppConnection().getConnection();
            PreparedStatement preparedStatement =
                    connection.prepareStatement(UR_CREATE.getScript());
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getAddress());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new AppException(USER_REPOSITORY_ERROR_01, e);
        }
    }

    @Override
    public User read(Integer userId) throws AppException {
        User user = null;
        try{
            connection = new AppConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(UR_READ.getScript() + userId);
            user = User.builder()
                    .id(rs.getInt("userId"))
                    .name(rs.getString("name"))
                    .address(rs.getString("address"))
                    .build();
            statement.close();
            connection.close();
        }catch(SQLException e){
            throw new AppException(USER_REPOSITORY_ERROR_02, e);
        }
        return user;
    }

    @Override
    public User readByUserName(String userName) throws AppException {
        try{
            connection = new AppConnection().getConnection();

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(UR_READ_BY_NAME.getScript() + userName + "'");
            User user = User.builder()
                    .id(rs.getInt("userId"))
                    .name(rs.getString("name"))
                    .address(rs.getString("address"))
                    .build();
            statement.close();
            connection.close();
            return user;
        } catch(SQLException e){
            throw new AppException(USER_REPOSITORY_ERROR_03, e);
        }
    }


    @Override
    public List<User> readAll() throws AppException {
        List<User> users = null;
        try{
            connection = new AppConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(UR_READ_ALL.getScript());
            users = new ArrayList<>();
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
        }catch(SQLException e){
            throw new AppException(USER_REPOSITORY_ERROR_04, e);
        }
        return users;
    }

    @Override
    public void update(User user, String address) throws AppException {
        try{
            connection = new AppConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UR_UPDATE.getScript());
            preparedStatement.setString(1, address);
            preparedStatement.setInt(2, user.getId());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            throw new AppException(USER_REPOSITORY_ERROR_05, e);
        }
    }

    @Override
    public void delete(Integer userId) throws AppException {
        try{
            connection = new AppConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UR_DELETE.getScript());
            preparedStatement.setInt(1, userId);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            throw new AppException(USER_REPOSITORY_ERROR_06, e);
        }
    }


    @Override
    public void deleteByUserName(String userName) throws AppException {
        try{
            connection = new AppConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UR_DELETE_BY_NAME.getScript());
            preparedStatement.setString(1, userName);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
        }catch(SQLException e){
            throw new AppException(USER_REPOSITORY_ERROR_07, e);
        }

    }
}
