package by.lev;

import by.lev.domain.Account;
import by.lev.repository.account_repository.AccountRepository;
import by.lev.repository.account_repository.AccountRepositoryInterface;

import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws SQLException {

        AccountRepositoryInterface accountRepository = new AccountRepository();

        accountRepository.delete(6);

        List<Account> accounts = accountRepository.readAll();
        accounts.forEach(System.out::println);

    }
}
