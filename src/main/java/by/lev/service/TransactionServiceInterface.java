package by.lev.service;

import by.lev.domain.Transaction;

import java.util.List;

public interface TransactionServiceInterface {

    boolean addTransaction(Transaction transaction) throws Exception;

    Transaction showTransaction(int transactionId) throws Exception;

    List<Transaction> showTransactionsByAccountId(int accountId) throws Exception;

    List<Transaction> showAllTransactions() throws Exception;


}
