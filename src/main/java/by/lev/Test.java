package by.lev;

import by.lev.domain.Account;
import by.lev.domain.Transaction;
import by.lev.repository.account_repository.AccountRepository;
import by.lev.repository.account_repository.AccountRepositoryInterface;
import by.lev.repository.transaction_repository.TransactionRepository;
import by.lev.repository.transaction_repository.TransactionRepositoryInterface;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {
        TransactionRepositoryInterface trR = new TransactionRepository();
        List<Transaction> transactions = trR.readAll();
        transactions.forEach(System.out::println);
    }
}
