package by.lev.repository.account_repository;

import by.lev.connection_db.AppConnection;
import by.lev.domain.Account;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.lev.metainf.SQLScript.ACCOUNT_REPOSITORY_CREATE;
import static by.lev.metainf.SQLScript.ACCOUNT_REPOSITORY_DELETE;
import static by.lev.metainf.SQLScript.ACCOUNT_REPOSITORY_READ;
import static by.lev.metainf.SQLScript.ACCOUNT_REPOSITORY_READ_ALL;
import static by.lev.metainf.SQLScript.ACCOUNT_REPOSITORY_UPDATE;

public class AccountRepository implements AccountRepositoryInterface {
    @Override
    public void create(Account account) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ACCOUNT_REPOSITORY_CREATE.getValue());
        preparedStatement.setInt(1, account.getUserId());
        preparedStatement.setDouble(2, account.getBalance());
        preparedStatement.setString(3, account.getCurrency());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public Account read(Integer id) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(ACCOUNT_REPOSITORY_READ.getValue() + id);
        Account account = Account.builder()
                .id(rs.getInt("accountId"))
                .userId(rs.getInt("userId"))
                .balance(rs.getDouble("balance"))
                .currency(rs.getString("currency"))
                .build();
        statement.close();
        connection.close();
        return account;
    }

    @Override
    public List<Account> readAll() throws SQLException {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(ACCOUNT_REPOSITORY_READ_ALL.getValue());
        List<Account> accounts = new ArrayList<>();
        while (rs.next()){
            Account account = Account.builder()
                    .id(rs.getInt("accountId"))
                    .userId(rs.getInt("userId"))
                    .balance(rs.getDouble("balance"))
                    .currency(rs.getString("currency"))
                    .build();
            accounts.add(account);
        }
        statement.close();
        connection.close();
        return accounts;
    }

    @Override
    public void update(Account account, Integer accountId) throws SQLException {
        Double balance = account.getBalance();
        update(balance, accountId);
    }

    @Override
    public void update(Double balance, Integer accountId) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ACCOUNT_REPOSITORY_UPDATE.getValue());
        preparedStatement.setDouble(1, balance);
        preparedStatement.setInt(2, accountId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public void delete(Integer accountId) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(ACCOUNT_REPOSITORY_DELETE.getValue());
        preparedStatement.setInt(1, accountId);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
