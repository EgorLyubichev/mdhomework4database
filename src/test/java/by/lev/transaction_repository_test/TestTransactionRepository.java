package by.lev.transaction_repository_test;

import by.lev.app_exception.AppException;
import by.lev.connection_db.AppConnection;
import by.lev.domain.Transaction;
import by.lev.repository.TransactionRepository;
import by.lev.repository.TransactionRepositoryInterface;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class TestTransactionRepository {
    TransactionRepositoryInterface transactionRepository = new TransactionRepository();

    Transaction transaction;

    @BeforeMethod
    public void initializeTestTransaction(){
        transaction = Transaction.builder()
                .accountId(0)
                .amount(100.0)
                .build();
    }

    private int getLatestIdFromTransactionsTable() throws SQLException {
        Connection connection = new AppConnection().getConnection();
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery(
                "select seq from sqlite_sequence where name='Transactions';"
        );
        int latestId =  rs.getInt( "seq");
        statement.close();
        connection.close();
        return latestId;
    }

    @Test
    public void testCreateAndRead() throws AppException, SQLException {
        transactionRepository.create(transaction);
        int latestId = getLatestIdFromTransactionsTable();
        Transaction expected = transactionRepository.read(latestId);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(transaction.getAccountId(), expected.getAccountId());
        softAssert.assertEquals(transaction.getAmount(), expected.getAmount());
        softAssert.assertAll();
    }

    @Test
    public void testReadAllByAccountId() throws AppException, SQLException {
        transactionRepository.create(transaction);
        int latestId = getLatestIdFromTransactionsTable();
        List<Transaction> transactions = transactionRepository.readAllByAccountId(transaction.getAccountId());
        Transaction expected = transactions.get(transactions.size()-1);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(latestId, expected.getTransactionId());
        softAssert.assertEquals(transaction.getAccountId(), expected.getAccountId());
        softAssert.assertEquals(transaction.getAmount(), expected.getAmount());
        softAssert.assertAll();
    }

    @Test
    public void testReadAll() throws AppException, SQLException {
        List<Transaction> transactions = transactionRepository.readAll();
        int latestId = getLatestIdFromTransactionsTable();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(transactions.size(), latestId);
        softAssert.assertFalse(transactions.isEmpty());
        softAssert.assertAll();
    }


}
