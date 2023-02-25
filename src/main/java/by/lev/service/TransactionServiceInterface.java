package by.lev.service;

import by.lev.app_exception.AppException;
import by.lev.domain.Transaction;

import java.util.List;

public interface TransactionServiceInterface {

    boolean addTransaction(Transaction transaction) throws AppException;

    Transaction showTransaction(int transactionId) throws AppException;

    List<Transaction> showTransactionsByAccountId(int accountId) throws AppException;

    List<Transaction> showAllTransactions() throws AppException;


}
