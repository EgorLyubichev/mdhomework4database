package by.lev.repository.transaction_repository;

import by.lev.connection_db.AppConnection;
import by.lev.domain.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.lev.metainf.SQLScript.TR_CREATE;
import static by.lev.metainf.SQLScript.TR_READ;
import static by.lev.metainf.SQLScript.TR_READ_ALL;
import static by.lev.metainf.SQLScript.TR_READ_BY_ACCOUNT_ID;

public class TransactionRepository implements TransactionRepositoryInterface {
    @Override
    public boolean create(Transaction transaction) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(TR_CREATE.getScript());
        preparedStatement.setInt(1, transaction.getAccountId());
        preparedStatement.setDouble(2, transaction.getAmount());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return true;
    }

    @Override
    public Transaction read(Integer value) throws SQLException {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(TR_READ.getScript() + value);
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
        ResultSet rs = statement.executeQuery(TR_READ_BY_ACCOUNT_ID.getScript() + accountId);
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
        ResultSet rs = statement.executeQuery(TR_READ_ALL.getScript());
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
}
