package by.lev.repository.transaction_repository;

import by.lev.connection_db.AppConnection;
import by.lev.domain.Transaction;
import by.lev.metainf.SQLScript;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.lev.metainf.SQLScript.TRANSACTION_REPOSITORY_CREATE;
import static by.lev.metainf.SQLScript.TRANSACTION_REPOSITORY_READ;
import static by.lev.metainf.SQLScript.TRANSACTION_REPOSITORY_READ_ALL;
import static by.lev.metainf.SQLScript.TRANSACTION_REPOSITORY_READ_BY_ACCOUNT_ID;

public class TransactionRepository implements TransactionRepositoryInterface {
    @Override
    public void create(Transaction transaction) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(TRANSACTION_REPOSITORY_CREATE.getValue());
        preparedStatement.setInt(1, transaction.getAccountId());
        preparedStatement.setDouble(2, transaction.getAmount());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    @Override
    public Transaction read(Integer value) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(TRANSACTION_REPOSITORY_READ.getValue() + value);
        Transaction transaction = Transaction.builder()
                .transactionId(rs.getInt("transactionId"))
                .accountId(rs.getInt("accountId"))
                .amount(rs.getDouble("amount"))
                .build();
        statement.close();
        connection.close();
        return transaction;
    }

    @Override
    public List<Transaction> readAllByAccountId(Integer accountId) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(TRANSACTION_REPOSITORY_READ_BY_ACCOUNT_ID.getValue() + accountId);
        List<Transaction> transactions = new ArrayList<>();
        while (rs.next()) {
            Transaction transaction = Transaction.builder()
                    .transactionId(rs.getInt("transactionId"))
                    .accountId(rs.getInt("accountId"))
                    .amount(rs.getDouble("amount"))
                    .build();
            transactions.add(transaction);
        }
        statement.close();
        connection.close();
        return transactions;
    }

    @Override
    public List<Transaction> readAll() throws SQLException {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(TRANSACTION_REPOSITORY_READ_ALL.getValue());
        List<Transaction> transactions = new ArrayList<>();
        while (rs.next()) {
            Transaction transaction = Transaction.builder()
                    .transactionId(rs.getInt("transactionId"))
                    .accountId(rs.getInt("accountId"))
                    .amount(rs.getDouble("amount"))
                    .build();
            transactions.add(transaction);
        }
        statement.close();
        connection.close();
        return transactions;
    }

    /**
     *Cannot update a transaction!
     */
    @Override
    public void update(Transaction transaction, Integer value) throws SQLException {
        throw new SQLException();
    }
    /**
     *Cannot delete a transaction!
     */
    @Override
    public void delete(Integer value) throws SQLException {
        throw new SQLException();
    }
}
