package by.lev.repository;

import by.lev.app_exception.AppException;
import by.lev.domain.Account;
import by.lev.repository.CRUDOperable;

import java.util.List;

public interface AccountRepositoryInterface extends CRUDOperable<Account, Integer> {

    List<Account> readAccountsByUserId(int userId) throws AppException;

    void update(Account account) throws AppException;

    void deleteUserAccountsByUserId(int userId) throws AppException;
}
