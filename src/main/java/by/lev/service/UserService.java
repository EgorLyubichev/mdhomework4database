package by.lev.service;

import by.lev.app_exception.AppException;
import by.lev.domain.Account;
import by.lev.domain.User;
import by.lev.repository.account_repository.AccountRepository;
import by.lev.repository.account_repository.AccountRepositoryInterface;
import by.lev.repository.user_repository.UserRepository;
import by.lev.repository.user_repository.UserRepositoryInterface;

import java.util.List;

public class UserService implements UserServiceInterface {

    private UserRepositoryInterface userRepository = new UserRepository();

    @Override
    public User addUser(User userSource) {
        User userDestination = null;
        try {
            userRepository.create(userSource);
            userDestination = userRepository.readByUserName(userSource.getName());
            return userDestination;
        } catch (AppException e) {
            System.err.println(e.getError() + ": " + e.getError().getErrorLocation());
            System.err.println(e.getCause());
        }
        return userDestination;
    }

    @Override
    public User readUser(int userId) throws Exception {
        return userRepository.read(userId);
    }

    @Override
    public List<User> readAllUsers() throws Exception {
        return userRepository.readAll();
    }

    /**
     * Обновить у пользователя можно только поле 'адрес'.
     */
    @Override
    public User updateUserAddress(User user, String address) throws Exception {
        userRepository.update(user, address);
        return userRepository.read(user.getId());
    }


    /**
     * Удалить пользователя можно только в том случае, если у него на балансах 0,
     * или у пользователя нет аккаунтов.
     */
    @Override
    public boolean deleteUser(User user) throws Exception {
        AccountRepositoryInterface ar = new AccountRepository();
        List<Account> userAccounts = ar.readAccountsByUserId(user.getId());
        double balance = 0.0;
        for (Account account : userAccounts) {
            balance = +account.getBalance();
        }
        if (balance == 0) {
            userRepository.delete(user.getId());
            return true;
        }
        return false;
    }
}