package by.lev.service;

import by.lev.app_exception.AppException;
import by.lev.domain.Transaction;

import java.util.List;

public interface TransactionServiceInterface {

    boolean addTransaction(Transaction transaction);

    Transaction getTransaction(int transactionId);

    List<Transaction> getTransactionsByAccountId(int accountId);

    List<Transaction> getAllTransactions();


}
