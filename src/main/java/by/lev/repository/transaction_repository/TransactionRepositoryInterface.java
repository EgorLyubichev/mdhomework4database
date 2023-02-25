package by.lev.repository.transaction_repository;

import by.lev.domain.Transaction;
import by.lev.repository.CRUDOperable;

import java.sql.SQLException;
import java.util.List;

public interface TransactionRepositoryInterface {

    boolean create(Transaction transaction) throws SQLException;

    Transaction read(Integer value) throws SQLException;

    List<Transaction> readAllByAccountId(Integer accountId) throws SQLException;

    List<Transaction> readAll() throws SQLException;

}
