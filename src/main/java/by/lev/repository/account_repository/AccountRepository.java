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

import static by.lev.metainf.SQLScript.AR_CREATE;
import static by.lev.metainf.SQLScript.AR_DELETE;
import static by.lev.metainf.SQLScript.AR_DELETE_ACCOUNTS_BY_USER_ID;
import static by.lev.metainf.SQLScript.AR_READ;
import static by.lev.metainf.SQLScript.AR_READ_ALL;
import static by.lev.metainf.SQLScript.AR_READ_BY_USER_ID;
import static by.lev.metainf.SQLScript.AR_UPDATE;

public class AccountRepository implements AccountRepositoryInterface {
    @Override
    public void create(Account account) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(AR_CREATE.getScript());
        preparedStatement.setInt(1, account.getUserId());
        preparedStatement.setDouble(2, account.getBalance());
        preparedStatement.setString(3, account.getCurrency().toUpperCase());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public Account read(Integer accountId) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(AR_READ.getScript() + accountId);
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
    public List<Account> readAccountsByUserId(int userId) throws Exception {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(AR_READ_BY_USER_ID.getScript() + userId);
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
    public List<Account> readAll() throws SQLException {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(AR_READ_ALL.getScript());
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
    public void update(Account account) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        PreparedStatement ps = connection.prepareStatement(AR_UPDATE.getScript());
        ps.setDouble(1, account.getBalance());
        ps.setInt(2, account.getId());
        ps.executeUpdate();
        ps.close();
        connection.close();
    }

    @Override
    public void deleteUserAccountsByUserId(int userId) throws Exception {
        Connection connection = new AppConnection().getConnection();
        PreparedStatement ps = connection.prepareStatement(AR_DELETE_ACCOUNTS_BY_USER_ID.getScript());
        ps.setInt(1, userId);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }


    @Override
    public void delete(Integer accountId) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        PreparedStatement ps = connection.prepareStatement(AR_DELETE.getScript());
        ps.setInt(1, accountId);
        ps.executeUpdate();
        ps.close();
        connection.close();
    }
}
