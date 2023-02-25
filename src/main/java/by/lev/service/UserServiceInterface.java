package by.lev.service;

import by.lev.domain.User;

import java.util.List;

public interface UserServiceInterface {

    User addUser(User userSource) throws Exception;

    User readUser(int userId) throws Exception;

    List<User> readAllUsers() throws Exception;

    User updateUserAddress(User user, String address)throws Exception;

    boolean deleteUser(User user) throws Exception;
}
