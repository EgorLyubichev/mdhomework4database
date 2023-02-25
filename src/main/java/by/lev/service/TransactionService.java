package by.lev.service;

import by.lev.app_exception.AppException;
import by.lev.domain.Transaction;
import by.lev.repository.transaction_repository.TransactionRepository;
import by.lev.repository.transaction_repository.TransactionRepositoryInterface;

import java.util.List;

public class TransactionService implements TransactionServiceInterface{

    TransactionRepositoryInterface trr = new TransactionRepository();

    @Override
    public boolean addTransaction(Transaction transaction) throws AppException {
        return trr.create(transaction);
    }

    @Override
    public Transaction showTransaction(int transactionId) throws AppException {
        return trr.read(transactionId);
    }

    @Override
    public List<Transaction> showTransactionsByAccountId(int accountId) throws AppException {
        return trr.readAllByAccountId(accountId);
    }

    @Override
    public List<Transaction> showAllTransactions() throws AppException {
        return trr.readAll();
    }
}
