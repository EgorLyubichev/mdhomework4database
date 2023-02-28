package by.lev.service;

import by.lev.app_exception.AppException;
import by.lev.domain.Account;
import by.lev.domain.Transaction;
import by.lev.repository.AccountRepository;
import by.lev.repository.AccountRepositoryInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AccountService implements AccountServiceInterface {

    private final AccountRepositoryInterface accountRepository = new AccountRepository();

    @Override
    public boolean addAccount(Account account) {
        try {
            List<Account> userAccounts = accountRepository.readAccountsByUserId(account.getUserId());
            Set<String> currencies = new HashSet<>();
            for (Account userAccount : userAccounts) {
                currencies.add(userAccount.getCurrency());
            }
            if (!currencies.contains(account.getCurrency().toUpperCase())) {
                accountRepository.create(account);
                return true;
            }
        } catch (AppException e) {
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return false;
    }

    @Override
    public boolean topUpTheBalance(Account account, String amount) {
        if (amount.isEmpty()) {
            return false;
        }
        if (!checkAmountCorrectness(amount)) {
            return false;
        }
        if (!checkLengthOfStringOfAmount(amount)) {
            return false;
        }
        double moneyValue = Double.parseDouble(amount);
        if (!checkAcceptableValueOfTheNumber(moneyValue)) {
            return false;
        }
        if (account.getBalance() + moneyValue > 2_000_000_000) {
            return false;
        }
        try {
            TransactionServiceInterface transactionService = new AppTransactionService();
            transactionService.addTransaction(
                    Transaction.builder()
                            .amount(moneyValue)
                            .accountId(account.getId())
                            .build());
            account.setBalance(account.getBalance() + moneyValue);
            accountRepository.update(account);
            return true;
        } catch (AppException e) {
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return false;
    }

    public boolean withdrawMoneyFromTheAccount(Account account, String amount) {
        if (amount.isEmpty()) {
            return false;
        }
        if (!checkAmountCorrectness(amount)) {
            return false;
        }
        if (!checkLengthOfStringOfAmount(amount)) {
            return false;
        }
        double moneyValue = Double.parseDouble(amount);
        if (!checkAcceptableValueOfTheNumber(moneyValue)) {
            return false;
        }
        if (account.getBalance() - moneyValue < 0) {
            return false;
        }
        try {
            String strValueToTransaction = "-" + moneyValue;
            double doubleValueToTransaction = Double.parseDouble(strValueToTransaction);
            TransactionServiceInterface transactionService = new AppTransactionService();
            transactionService.addTransaction(
                    Transaction.builder()
                            .amount(doubleValueToTransaction)
                            .accountId(account.getId())
                            .build());
            account.setBalance(account.getBalance() - moneyValue);
            accountRepository.update(account);
            return true;
        } catch (AppException e) {
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return false;
    }

    private boolean checkAmountCorrectness(String amount) {
        Pattern pattern = Pattern.compile("\\d*\\.?\\d{0,3}");
        Matcher matcher = pattern.matcher(amount);
        return matcher.matches();
    }

    private boolean checkLengthOfStringOfAmount(String amount) {
        return (amount.length() <= 13);
    }

    private boolean checkAcceptableValueOfTheNumber(Double amount) {
        return (amount > 0 && amount <= 100_000_000);
    }

    public List<Account> getUserAccountsByUserId(int userId) {
        List<Account> accounts = new ArrayList<>();
        try {
            accounts = accountRepository.readAccountsByUserId(userId);
        } catch (AppException e) {
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return accounts;
    }

    public Account getAccountByAccountId(int accountId) {
        Account account = new Account();
        try {
            account = accountRepository.read(accountId);
        } catch (AppException e) {
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return account;
    }

    /**
     * Удалить аккаунт можно только в том случае, если на балансе 0.
     */
    public boolean deleteAccountByAccountId(int accountId) {
        try{
            Account account = getAccountByAccountId(accountId);
            if (account.getBalance() == 0) {
                accountRepository.delete(accountId);
                return true;
            }
            return false;
        }catch (AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return false;
    }

    /**
     * Удалить аккаунты пользователя можно только в том случае, если на балансах 0.
     */
    public boolean deleteUserAccountsByUserId(int userId) {
        try{
            List<Account> accounts = accountRepository.readAccountsByUserId(userId);
            double balance = 0.0;
            for (Account account : accounts) {
                balance += account.getBalance();
            }
            if (balance == 0) {
                accountRepository.deleteUserAccountsByUserId(userId);
                return true;
            }
            return false;
        }catch (AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return false;
    }
}