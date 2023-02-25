package by.lev.repository.transaction_repository;

import by.lev.app_exception.AppException;
import by.lev.connection_db.AppConnection;
import by.lev.domain.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static by.lev.app_exception.AppError.TRANSACTION_REPOSITORY_ERROR_01;
import static by.lev.app_exception.AppError.TRANSACTION_REPOSITORY_ERROR_02;
import static by.lev.app_exception.AppError.TRANSACTION_REPOSITORY_ERROR_03;
import static by.lev.app_exception.AppError.TRANSACTION_REPOSITORY_ERROR_04;
import static by.lev.metainf.SQLScript.TR_CREATE;
import static by.lev.metainf.SQLScript.TR_READ;
import static by.lev.metainf.SQLScript.TR_READ_ALL;
import static by.lev.metainf.SQLScript.TR_READ_BY_ACCOUNT_ID;

public class TransactionRepository implements TransactionRepositoryInterface {
    Connection connection;

    @Override
    public boolean create(Transaction transaction) throws AppException {
        try{
            connection = new AppConnection().getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(TR_CREATE.getScript());
            preparedStatement.setInt(1, transaction.getAccountId());
            preparedStatement.setDouble(2, transaction.getAmount());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        }catch(SQLException e){
            throw new AppException(TRANSACTION_REPOSITORY_ERROR_01, e);
        }
    }

    @Override
    public Transaction read(Integer value) throws AppException {
        Transaction transaction = null;
        try{
            connection = new AppConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(TR_READ.getScript() + value);
            transaction = Transaction.builder()
                    .transactionId(rs.getInt("transactionId"))
                    .accountId(rs.getInt("accountId"))
                    .amount(rs.getDouble("amount"))
                    .build();
            statement.close();
            connection.close();
            return transaction;
        }catch(SQLException e){
            throw new AppException(TRANSACTION_REPOSITORY_ERROR_02, e);
        }
    }

    @Override
    public List<Transaction> readAllByAccountId(Integer accountId) throws AppException {
        List<Transaction> transactions = null;
        try{
            connection = new AppConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(TR_READ_BY_ACCOUNT_ID.getScript() + accountId);
            transactions = new ArrayList<>();
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
        }catch(SQLException e){
            throw new AppException(TRANSACTION_REPOSITORY_ERROR_03, e);
        }
    }

    @Override
    public List<Transaction> readAll() throws AppException {
        List<Transaction> transactions = null;
        try{
            connection = new AppConnection().getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(TR_READ_ALL.getScript());
            transactions = new ArrayList<>();
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
        }catch(SQLException e){
            throw new AppException(TRANSACTION_REPOSITORY_ERROR_04, e);
        }
    }
}
