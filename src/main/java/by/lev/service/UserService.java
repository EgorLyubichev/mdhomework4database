package by.lev.service;

import by.lev.app_exception.AppException;
import by.lev.domain.Account;
import by.lev.domain.User;
import by.lev.repository.AccountRepository;
import by.lev.repository.AccountRepositoryInterface;
import by.lev.repository.UserRepository;
import by.lev.repository.UserRepositoryInterface;

import java.util.ArrayList;
import java.util.List;

public class UserService implements UserServiceInterface {

    private final UserRepositoryInterface userRepository = new UserRepository();

    @Override
    public User addUser(User user) {
        User userDest = new User();
        try{
            userRepository.create(user);
            userDest = userRepository.readByUserName(user.getName());
        }catch (AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return userDest;
    }

    @Override
    public User readUser(int userId) {
        User user = new User();
        try{
            user = userRepository.read(userId);
        }catch (AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return user;
    }

    @Override
    public User readUserByUserName(String userName) {
        User user = new User();
        try{
            user = userRepository.readByUserName(userName);
        }catch(AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return user;
    }


    @Override
    public List<User> readAllUsers() {
        List<User> users = new ArrayList<>();
        try{
            users = userRepository.readAll();
        }catch (AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return users;
    }

    /**
     * Обновить у пользователя можно только поле 'адрес'.
     */
    @Override
    public User updateUserAddress(User user, String address) {
        User userDest = new User();
        try{
            userRepository.update(user, address);
            userDest = userRepository.read(user.getId());
        }catch (AppException e){
            System.err.println(e.getError());
            System.err.println(e.getError().getErrorLocation());
        }
        return userDest;
    }


    /**
     * Удалить пользователя можно только в том случае, если у него на балансах 0,
     * или у пользователя нет аккаунтов.
     */
    @Override
    public boolean deleteUser(User user) {
        try{
            AccountRepositoryInterface ar = new AccountRepository();
            List<Account> userAccounts = ar.readAccountsByUserId(user.getId());
            double balance = 0.0;
            for (Account account : userAccounts) {
                balance += account.getBalance();
            }
            if (balance == 0) {
                userRepository.delete(user.getId());
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