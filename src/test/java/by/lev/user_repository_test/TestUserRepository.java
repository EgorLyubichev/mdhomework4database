package by.lev.user_repository_test;

import by.lev.app_exception.AppException;
import by.lev.domain.User;
import by.lev.repository.UserRepository;
import by.lev.repository.UserRepositoryInterface;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertFalse;

public class TestUserRepository {

    UserRepositoryInterface userRepository = new UserRepository();
    User user;

    @BeforeMethod
    private void initializeTestUser(){
        user = User.builder()
                .id(0)
                .name("TestUserName")
                .address(null)
                .build();
    }

    @Test
    public void testCreateUserIntoUsersTable(){
        try{
            userRepository.create(user);
            User expected = userRepository.readByUserName(user.getName());

            SoftAssert checker = new SoftAssert();

            checker.assertTrue(expected.getId() > 0);
            checker.assertEquals(user.getName(), expected.getName());
            checker.assertNull(expected.getAddress());
            checker.assertAll();

        }catch(AppException e){
            e.getError();
        }
    }

    @Test
    public void testReadAll(){
        List<User> users = new ArrayList<>();
        try {
            users = userRepository.readAll();
        } catch (AppException e) {
            e.getError();
        }

        assertFalse(users.isEmpty());
    }

    @AfterMethod
    private void deleteTestUserFromTable(){
        try{
            userRepository.deleteByUserName("TestUserName");
        }catch (AppException e){
            e.getError();
        }
    }
}
