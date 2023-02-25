package by.lev.service;

import by.lev.domain.Account;

import java.util.List;

public interface AccountServiceInterface {

    boolean addAccount(Account account) throws Exception;

    boolean topUpTheBalance(Account account, String amount) throws Exception;

    boolean withdrawMoneyFromTheAccount(Account account, String amount) throws Exception;

    List<Account> getUserAccountsByUserId(int userId) throws Exception;

    Account getAccountByAccountId(int accountId) throws Exception;

    boolean deleteAccountByAccountId(int accountId)throws Exception;

    boolean deleteUserAccountsByUserId(int userId) throws Exception;

}