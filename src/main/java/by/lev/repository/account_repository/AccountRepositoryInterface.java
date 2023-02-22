package by.lev.repository.account_repository;

import by.lev.domain.Account;
import by.lev.repository.CRUDOperable;

import java.sql.SQLException;

public interface AccountRepositoryInterface extends CRUDOperable<Account, Integer> {

    void update(Double balance, Integer accountId) throws SQLException;
}
