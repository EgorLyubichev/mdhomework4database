package by.lev.repository.transaction_repository;

import by.lev.domain.Transaction;
import by.lev.repository.CRUDOperable;

import java.sql.SQLException;
import java.util.List;

public interface TransactionRepositoryInterface extends CRUDOperable<Transaction, Integer> {

    List<Transaction> readAllByAccountId(Integer accountId) throws SQLException;

}
