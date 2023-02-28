package by.lev.service;

import by.lev.app_exception.AppException;
import by.lev.domain.User;

import java.util.List;

public interface UserServiceInterface {

    User addUser(User userSource);

    User readUser(int userId);

    User readUserByUserName(String userName);

    List<User> readAllUsers();

    User updateUserAddress(User user, String address);

    boolean deleteUser(User user);
}
