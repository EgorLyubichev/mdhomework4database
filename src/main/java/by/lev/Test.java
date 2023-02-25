package by.lev;

import by.lev.domain.Account;
import by.lev.domain.User;
import by.lev.repository.user_repository.UserRepository;
import by.lev.repository.user_repository.UserRepositoryInterface;
import by.lev.service.AccountService;
import by.lev.service.AccountServiceInterface;
import by.lev.service.UserService;
import by.lev.service.UserServiceInterface;

import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {

 //       UserRepositoryInterface ur = new UserRepository();
        User user = new User();
        user.setName("Vasia");
   //     ur.create(user);

        System.out.println();

        UserServiceInterface us = new UserService();
        us.addUser(user);


    }
}
