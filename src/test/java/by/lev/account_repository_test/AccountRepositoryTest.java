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

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

public class AccountRepositoryTest {
    AccountRepositoryInterface accountRepository = new AccountRepository();
    Account account;

    @BeforeMethod
    private void initializeTestAccount(){
        account = Account.builder()
                .id(0)
                .userId(0)
                .balance(0.0)
                .currency("TEST_CURRENCY")
                .build();
    }

    @Test
    public void testCreateAccount() throws AppException {

            accountRepository.create(account);
            List<Account> accounts = accountRepository.readAccountsByUserId(account.getUserId());
            Account expectedAccount = accounts.get(accounts.size()-1);

            SoftAssert checker = new SoftAssert();

            checker.assertFalse(accounts.isEmpty());
            checker.assertTrue(expectedAccount.getId() > 0);
            checker.assertEquals(account.getUserId(), expectedAccount.getUserId());
            checker.assertEquals(account.getBalance(), expectedAccount.getBalance());
            checker.assertEquals(account.getCurrency(), expectedAccount.getCurrency());
            checker.assertAll();

            account = expectedAccount;
    }

    @Test
    public void testReadAccountById() throws AppException {
        accountRepository.create(account);
        List<Account> accounts = accountRepository.readAccountsByUserId(account.getUserId());
        account = accounts.get(accounts.size()-1);

        Account expected = accountRepository.read(account.getId());

        assertTrue(account.equals(expected));
    }

    @Test
    public void testReadAll() throws AppException {
        accountRepository.create(account);
        List<Account> accounts = new ArrayList<>();
        accounts = accountRepository.readAll();
        assertFalse(accounts.isEmpty());
    }

    @Test
    public void testUpdate() throws AppException {
        accountRepository.create(account);
        List<Account> accounts = new ArrayList<>();
        accounts = accountRepository.readAccountsByUserId(account.getUserId());
        account = accounts.get(accounts.size()-1);
        account.setBalance(1000.0);
        accountRepository.update(account);
        Account expected = accountRepository.read(account.getId());
        assertEquals(account.getBalance(), expected.getBalance());
    }


    @Test
    public void testDeleteAccountByAccountId()throws AppException{
        accountRepository.create(account);
        List<Account> accountsBeforeRemoving = accountRepository.readAccountsByUserId(account.getUserId());
        account = accountsBeforeRemoving.get(accountsBeforeRemoving.size()-1);
        accountRepository.delete(account.getId());
        List<Account> accountsAfterRemoving = accountRepository.readAll();
        Set<Integer> accountsIds = new HashSet<>();
        for (Account account:accountsAfterRemoving) {
            accountsIds.add(account.getId());
        }
        assertFalse(accountsIds.contains(account.getId()));
    }

    @AfterMethod
    private void deleteTestAccount(){
        try {
            accountRepository.deleteUserAccountsByUserId(account.getUserId());
        } catch (AppException e) {
            e.getError();
        }
    }
}
