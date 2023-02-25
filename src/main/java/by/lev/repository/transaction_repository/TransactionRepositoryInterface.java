package by.lev.repository.transaction_repository;

import by.lev.app_exception.AppException;
import by.lev.domain.Transaction;

import java.util.List;

public interface TransactionRepositoryInterface {

    boolean create(Transaction transaction) throws AppException;

    Transaction read(Integer value) throws AppException;

    List<Transaction> readAllByAccountId(Integer accountId) throws AppException;

    List<Transaction> readAll() throws AppException;

}
