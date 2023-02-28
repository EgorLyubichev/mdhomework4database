package by.lev.service;

import by.lev.app_exception.AppException;
import by.lev.domain.Account;

import java.util.List;

public interface AccountServiceInterface {

    boolean addAccount(Account account);

    boolean topUpTheBalance(Account account, String amount);

    boolean withdrawMoneyFromTheAccount(Account account, String amount);

    List<Account> getUserAccountsByUserId(int userId);

    Account getAccountByAccountId(int accountId);

    boolean deleteAccountByAccountId(int accountId);

    boolean deleteUserAccountsByUserId(int userId);

}