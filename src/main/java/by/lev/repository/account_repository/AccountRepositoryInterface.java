package by.lev.repository.account_repository;

import by.lev.domain.Account;
import by.lev.domain.User;
import by.lev.repository.CRUDOperable;

import java.sql.SQLException;
import java.util.List;

public interface AccountRepositoryInterface extends CRUDOperable<Account, Integer> {

    List<Account> readAccountsByUserId(int userId) throws Exception;

    void update(Account account) throws Exception;

    void deleteUserAccountsByUserId(int userId) throws Exception;
}
