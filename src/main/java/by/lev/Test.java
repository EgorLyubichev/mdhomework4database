package by.lev;

import by.lev.domain.Account;
import by.lev.service.AccountService;
import by.lev.service.AccountServiceInterface;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

        AccountServiceInterface as = new AccountService();

        Account account = as.getAccountByAccountId(3);
        System.out.println(account);
        System.out.println(as.withdrawMoneyFromTheAccount(account, "1018.22"));
        account = as.getAccountByAccountId(3);
        System.out.println(account);
        System.out.println(as.deleteUserAccountsByUserId(2));

    }
}
