package by.lev.service;

import by.lev.app_exception.AppException;
import by.lev.domain.Transaction;
import by.lev.repository.TransactionRepository;
import by.lev.repository.TransactionRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class AppTransactionService implements TransactionServiceInterface{

    private final TransactionRepositoryInterface transactionRepository = new TransactionRepository();

    @Override
    public boolean addTransaction(Transaction transaction) {
        try{
            return transactionRepository.create(transaction);
        }catch (AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return false;
    }

    @Override
    public Transaction getTransaction(int transactionId) {
        Transaction transaction = new Transaction();
        try{
            transaction = transactionRepository.read(transactionId);
        }catch (AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return transaction;
    }

    @Override
    public List<Transaction> getTransactionsByAccountId(int accountId) {
        List<Transaction> transactions = new ArrayList<>();
        try{
            transactions = transactionRepository.readAllByAccountId(accountId);
        }catch (AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return transactions;
    }

    @Override
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        try{
            transactions = transactionRepository.readAll();
        }catch (AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return transactions;
    }
}
