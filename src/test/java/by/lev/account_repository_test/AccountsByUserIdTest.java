package by.lev.account_repository_test;

import by.lev.app_exception.AppException;
import by.lev.domain.Account;
import by.lev.repository.AccountRepository;
import by.lev.repository.AccountRepositoryInterface;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertFalse;

public class AccountsByUserIdTest {

    AccountRepositoryInterface accountRepository = new AccountRepository();
    Account account1;
    Account account2;
    Account account3;

    @BeforeMethod
    private void initializeAccounts() throws AppException {
        account1 = Account.builder()
                .userId(0)
                .balance(100.0)
                .currency("TEST_CURRENCY_1")
                .build();
        account2 = Account.builder()
                .userId(0)
                .balance(200.0)
                .currency("TEST_CURRENCY_2")
                .build();
        account3 = Account.builder()
                .userId(0)
                .balance(300.0)
                .currency("TEST_CURRENCY_3")
                .build();
        accountRepository.create(account1);
        accountRepository.create(account2);
        accountRepository.create(account3);
    }

    @Test
    public void testReadAccountsByUserId() throws AppException {
        List<Account> accounts = new ArrayList<>();
        accounts = accountRepository.readAccountsByUserId(0);

        SoftAssert checker = new SoftAssert();
        checker.assertFalse(accounts.isEmpty());
        checker.assertEquals(accounts.size(), 3);
        checker.assertEquals(account1.getCurrency(), accounts.get(0).getCurrency());
        checker.assertEquals(account1.getBalance(), accounts.get(0).getBalance());
        checker.assertEquals(account2.getCurrency(), accounts.get(1).getCurrency());
        checker.assertEquals(account2.getBalance(), accounts.get(1).getBalance());
        checker.assertEquals(account3.getCurrency(), accounts.get(2).getCurrency());
        checker.assertEquals(account3.getBalance(), accounts.get(2).getBalance());
        checker.assertAll();
    }

    @Test
    public void testDeleteAccountsByUserId() throws AppException {
        accountRepository.deleteUserAccountsByUserId(account1.getUserId());
        List<Account> accounts = accountRepository.readAll();
        Set<Integer> usersId = new HashSet<>();
        for (Account account : accounts) {
            usersId.add(account.getUserId());
        }
        assertFalse(usersId.contains(account1.getUserId()));
    }

    @AfterMethod
    private void deleteTestAccounts() throws AppException {
        accountRepository.deleteUserAccountsByUserId(0);
    }
}
