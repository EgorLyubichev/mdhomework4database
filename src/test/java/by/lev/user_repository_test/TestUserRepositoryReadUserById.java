package by.lev.user_repository_test;

import by.lev.app_exception.AppException;
import by.lev.domain.User;
import by.lev.repository.UserRepository;
import by.lev.repository.UserRepositoryInterface;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TestUserRepositoryReadUserById {
    UserRepositoryInterface userRepository = new UserRepository();
    User user;

    @BeforeMethod
    private void initializeTestUser(){
        user = User.builder()
                .id(0)
                .name("testName")
                .address(null)
                .build();
        try {
            userRepository.create(user);
            user = userRepository.readByUserName(user.getName());
        } catch (AppException e) {
            e.getError();
        }
    }

    @Test
    public void testReadById(){
        try {
            User expected = userRepository.read(user.getId());
            Assert.assertTrue(expected.equals(user));
        } catch (AppException e) {
            e.getError();
        }
    }

    @AfterMethod
    private void deleteTestUserFromTable(){
        try {
            userRepository.delete(user.getId());
        } catch (AppException e) {
            e.getError();
        }
    }
}
