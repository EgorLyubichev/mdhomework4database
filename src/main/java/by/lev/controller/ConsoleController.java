package by.lev.controller;

import by.lev.domain.Account;
import by.lev.domain.Transaction;
import by.lev.domain.User;
import by.lev.service.AccountService;
import by.lev.service.AccountServiceInterface;
import by.lev.service.AppTransactionService;
import by.lev.service.TransactionServiceInterface;
import by.lev.service.UserService;
import by.lev.service.UserServiceInterface;

import java.util.List;
import java.util.Scanner;

public class ConsoleController {

    private final UserServiceInterface userService = new UserService();
    private final AccountServiceInterface accountService = new AccountService();
    private final TransactionServiceInterface transactionService = new AppTransactionService();

    User currentUser = new User();
    Account currentAccount = new Account();

    public void getStart() {
        System.out.println("= = = >>ДОБРО ПОЖАЛОВАТЬ<< = = =");
        System.out.println("<1> - вход\n<2> - регистрация\n<q> - выход");
        System.out.println("Ваш ход: ...");
        String input = scanString();
        switch (input) {
            case "1":
                getIn();
                break;
            case "2":
                getRegistrationNewUser();
                break;
            case "q":
                System.out.println("До свидания!");
                System.exit(0);
                break;
            default:
                System.out.println("Неверный ввод данных!");
                getStart();
        }
    }

    public void getIn() {
        System.out.println("Введите Ваш логин: ...");
        String login = scanString();
        if (login.equalsIgnoreCase("q")) {
            getStart();
        }
        boolean isLoginInDatabase = checkUserNameInDataBase(login);
        if (!isLoginInDatabase) {
            System.out.println("Пользователь с данным логином не зарегистрирован!");
            getIn();
        }
        System.out.println("Введите Ваш учетный номер: ...");
        String userIdAsString = scanString();
        int userId = 0;
        try {
            userId = Integer.parseInt(userIdAsString);
        } catch (NumberFormatException e) {
            System.err.println("Ввод должен быть исключительно цифрами!\n");
            getStart();
        }
        if (isUserIntoUsersTable(login, userId)) {
            System.out.println("Ваши данные подтверждены.\n");
            getUserMenu();
        } else {
            System.out.println("!!!Такого пользователя нет в списке клиентов нашего банка!!!");
            getStart();
        }
    }

    private boolean checkUserNameInDataBase(String userName) {
        List<User> users = userService.readAllUsers();
        for (User user : users) {
            if (user.getName().equalsIgnoreCase(userName)) {
                return true;
            }

        }
        return false;
    }

    public void getRegistrationNewUser() {
        System.out.println("Введите логин для Вашей учётной записи...");
        String userName = scanString();
        if (userName.equalsIgnoreCase("q")) {
            getStart();
        }
        if (userService.readUserByUserName(userName).getId() != 0) {
            System.out.println("Пользователь с таким именем уже существует в системе.");
            System.out.println("Предложите другой логин.");
            getRegistrationNewUser();
        }
        currentUser.setName(userName);
        System.out.println("Хотите ввести ваш адрес?");
        System.out.println("Если нет, то нажмите 'n' и 'Enter'");
        String address = scanString();
        address = address.trim();
        if (!address.equalsIgnoreCase("n")) {
            currentUser.setAddress(address);
        }
        currentUser = userService.addUser(currentUser);
        System.out.println("Новый пользователь зарегистирован  успешно!");
        System.out.println("Ваши учётные данные: " + currentUser + "\n");
        getUserMenu();
    }

    private boolean isUserIntoUsersTable(String login, int userId) {
        List<User> users = userService.readAllUsers();
        for (User userFromList : users) {
            if (userFromList.getName().equalsIgnoreCase(login)
                    && userFromList.getId() == userId) {
                currentUser = userFromList;
                return true;
            }
        }
        return false;
    }

    public void getUserMenu() {
        System.out.println("= = = >>СТРАНИЧКА  КЛИЕНТА<< = = =");
        System.out.println("<1> - Ваша учётная запись.");
        System.out.println("<2> - Ваши аккаунты.");
        System.out.println("<0> - Главная страничка.");
        System.out.println("<q> - Выход из программы.");
        System.out.println("Ваш ход...");
        String input = scanString();
        switch (input) {
            case "1":
                getUserDataMenu();
                break;
            case "2":
                getAccountsMenu();
                break;
            case "0":
                currentUser = User.builder()
                        .id(0)
                        .name(null)
                        .address(null)
                        .build();
                currentAccount = Account.builder()
                        .id(0)
                        .userId(0)
                        .balance(0.0)
                        .currency(null)
                        .build();
                getStart();
                break;
            case "q":
                System.out.println("До свидания!");
                System.exit(0);
                break;
            default:
                System.out.println("Ваш ввод не внушает доверия ;-)");
                System.out.println("Давайте ещё раз!");
                getUserMenu();
        }
    }

    public void getUserDataMenu() {
        System.out.println(">>>ВАША УЧЕТНАЯ ЗАПИСЬ<<<");
        System.out.println("Учётный номер: " + currentUser.getId() + ".");
        System.out.println("Учётный логин: " + currentUser.getName() + ".");
        if (currentUser.getAddress() == null) {
            System.out.println("Ваш адрес не указан.\n");
        } else {
            System.out.println("Ваш адрес: " + currentUser.getAddress() + "\n");
        }
        System.out.println("Вы можете");
        System.out.println("<1> - Изменить адрес.");
        System.out.println("<2> - Удалить данную учетную запись.");
        System.out.println("<3> - Вернуться к предыдущему меню.");
        System.out.println("<0> - Главная страничка.");
        System.out.println("<q> - Выход.");
        String input = scanString();
        switch (input) {
            case "1":
                changeUserAddress();
                break;
            case "2":
                deleteUser();
                break;
            case "3":
                getUserMenu();
                break;
            case "0":
                currentUser = User.builder()
                        .id(0)
                        .name(null)
                        .address(null)
                        .build();
                currentAccount = Account.builder()
                        .id(0)
                        .userId(0)
                        .balance(0.0)
                        .currency(null)
                        .build();
                getStart();
                break;
            case "q":
                System.out.println("До свидания!");
                System.exit(0);
            default:
                System.out.println("Ваш ввод не внушает доверия ;-)");
                System.out.println("Давайте ещё раз!");
                getUserDataMenu();
        }
    }

    public void changeUserAddress() {
        System.out.println("Введите Ваш новый адрес: ...");
        String address = scanString();
        currentUser = userService.updateUserAddress(currentUser, address);
        System.out.println("Ваш адрес изменён!");
        getUserDataMenu();
    }

    public void deleteUser() {
        System.out.println("Вы уверены в соём решении удалить данную учётную запись?");
        System.out.println("'y' - да, 'n' - нет.");
        System.out.println("Ваш ход...");
        String input = scanString();
        switch (input) {
            case "y":
                if (userService.deleteUser(currentUser)) {
                    currentUser = User.builder()
                            .id(0)
                            .name(null)
                            .address(null)
                            .build();
                    System.out.println("Данная учётная запись успешно удалена");
                    getStart();
                } else {
                    System.out.println("Вы не можете удалить данную учётную запись.");
                    System.out.println("Возможно на Вашем балансе имеются средства.\n");
                    getUserDataMenu();
                }
                break;
            case "n":
                getUserDataMenu();
                break;
            default:
                System.out.println("Нет решения? Не тыкай куда попало!");
                deleteUser();
        }
    }

    public void getAccountsMenu() {
        System.out.println(">>>ВАШИ АККАУНТЫ<<<");
        System.out.println("<1> - Имеющиеся аккаунты.");
        System.out.println("<2> - Перейти к определенному аккаунту.");
        System.out.println("<3> - Вернуться на 'страничку клиента'.");
        System.out.println("<0> - Главная страничка.");
        System.out.println("<q> - Выход.");
        String input = scanString();
        switch (input) {
            case "1":
                showUserAccountsAndAccountMenu();
                break;
            case "2":
                moveToTheAccount();
                break;
            case "3":
                getUserMenu();
                break;
            case "0":
                currentUser = User.builder()
                        .id(0)
                        .name(null)
                        .address(null)
                        .build();
                currentAccount = Account.builder()
                        .id(0)
                        .userId(0)
                        .balance(0.0)
                        .currency(null)
                        .build();
                getStart();
                break;
            case "q":
                System.out.println("До свидания!");
                System.exit(0);
            default:
                System.out.println("Неверный ввод данных!");
                getAccountsMenu();
        }
    }

    public void showUserAccountsAndAccountMenu() {
        List<Account> accounts =
                accountService.getUserAccountsByUserId(currentUser.getId());
        if (accounts.isEmpty()) {
            System.out.println("У Вас ещё не зарегистрировано ни одного счёта!");
        }
        accounts.forEach(System.out::println);
        System.out.println("Вы можете:");
        System.out.println("<1> - Создать новый аккаунт.");
        System.out.println("<2> - Удалить определенный аккаунт.");
        System.out.println("<3> - Удалить все аккаунты.");
        System.out.println("<4> - Предыдущее меню.");
        System.out.println("<q> - Выход.");
        String input = scanString();
        switch (input) {
            case "1":
                createNewAccount();
                break;
            case "2":
                deleteOneOfAccountsOfUser();
                break;
            case "3":
                deleteAllAccounts();
                break;
            case "4":
                getAccountsMenu();
                break;
            case "q":
                System.out.println("До свидания!");
                System.exit(0);
            default:
                System.out.println("Неверный ввод данных!");
                getAccountsMenu();
        }
    }

    public void createNewAccount() {
        Account account = new Account();
        System.out.println("В какой валюте Вы хотите открыть счёт?");
        String currency = scanString();
        account.setCurrency(currency);
        account.setUserId(currentUser.getId());
        if (accountService.addAccount(account)) {
            System.out.println("Новый счет успешно добавлен!\n");
            showUserAccountsAndAccountMenu();
        }
    }

    public void deleteOneOfAccountsOfUser() {
        System.out.println("Введите номер счёта, который планируете удалить...");
        String accountIdInString = scanString();
        int accountId = 0;
        try {
            accountId = Integer.parseInt(accountIdInString);
        } catch (NumberFormatException e) {
            System.out.println("Неверный ввод!");
            deleteOneOfAccountsOfUser();
        }
        Account account = accountService.getAccountByAccountId(accountId);
        if (account.getUserId() == currentUser.getId()) {
            boolean isDeleted = accountService.deleteAccountByAccountId(accountId);
            if (isDeleted) {
                System.out.println("Указанный счёт успешно удалён!\n");
                showUserAccountsAndAccountMenu();
            } else {
                System.out.println("Указанный счёт не может быть удалён!");
                System.out.println("Возможно на балансе имеются средства.");
                showUserAccountsAndAccountMenu();
            }
        } else {
            System.out.println("Вы пытаетесь обратиться к стороннему счёту!");
            showUserAccountsAndAccountMenu();
        }
    }

    public void deleteAllAccounts() {
        if (accountService.deleteUserAccountsByUserId(currentUser.getId())) {
            System.out.println("Все Ваши счета удалены!");
            showUserAccountsAndAccountMenu();
        } else {
            System.out.println("В удалении отказано! Проверьте балансы Ваших счетов!");
            showUserAccountsAndAccountMenu();
        }
    }

    public void moveToTheAccount() {
        System.out.println("Введите номер счёта...");
        String input = scanString();
        int accountId = 0;
        try {
            accountId = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Не верный ввод данных!");
            moveToTheAccount();
        }
        Account account = accountService.getAccountByAccountId(accountId);
        if (account.getUserId() == currentUser.getId()) {
            currentAccount = account;
            accountMenu();
        }
    }

    public void accountMenu() {
        System.out.println("Текущий счёт: " + currentAccount);
        System.out.println("Операции по счёту:");
        System.out.println("<1> - Положить деньги на счёт.");
        System.out.println("<2> - Снять деньги со счёта.");
        System.out.println("<3> - Все транзакции данного счёта.");
        System.out.println("<4> - Предыдущее меню.");
        System.out.println("<q> - Выход.");
        String input = scanString();
        switch (input) {
            case "1":
                pushMoneyToAccount();
                break;
            case "2":
                pullMoneyFromAccount();
                break;
            case "3":
                showTransactions();
                break;
            case "4":
                showUserAccountsAndAccountMenu();
                break;
            case "q":
                System.out.println("До свидания!");
                System.exit(0);
            default:
                System.out.println("Неверный ввод!");
                accountMenu();
        }
    }

    public void pushMoneyToAccount() {
        System.out.println("Введите сумму, которую хотите положить на счёт...");
        String money = scanString();
        if (accountService.topUpTheBalance(currentAccount, money)) {
            System.out.println("Транзакция прошла успешно!");
            currentAccount = accountService.getAccountByAccountId(currentAccount.getId());
            accountMenu();
        } else {
            System.out.println("Транзакция не может быть исполнена!");
            accountMenu();
        }
    }

    public void pullMoneyFromAccount() {
        System.out.println("Введите сумму, которую хотите снять со счёта...");
        String money = scanString();
        if (accountService.withdrawMoneyFromTheAccount(currentAccount, money)) {
            System.out.println("Транзакция прошла успешно!");
            currentAccount = accountService.getAccountByAccountId(currentAccount.getId());
            accountMenu();
        } else {
            System.out.println("Транзакция не может быть исполнена!");
            accountMenu();
        }
    }

    public void showTransactions() {
        List<Transaction> transactions = transactionService.getTransactionsByAccountId(currentAccount.getId());
        if(transactions.isEmpty()){
            System.out.println("Ни каких операций по данному счёту не производилось.");
        }else{
            for (Transaction transaction:transactions) {
                System.out.println(transaction);
            }
        }
        accountMenu();
    }

    private String scanString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
