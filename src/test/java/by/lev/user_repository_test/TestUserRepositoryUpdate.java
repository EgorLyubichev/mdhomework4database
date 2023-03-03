package by.lev.user_repository_test;

import by.lev.app_exception.AppException;
import by.lev.domain.User;
import by.lev.repository.UserRepository;
import by.lev.repository.UserRepositoryInterface;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestUserRepositoryUpdate {
    UserRepositoryInterface userRepository = new UserRepository();
    User userBeforeTest;

    @BeforeMethod
    private void initializeUserBeforeTest(){
        User user = User.builder()
                .id(0)
                .name("TestUserName")
                .address(null)
                .build();
        try {
            userRepository.create(user);
            userBeforeTest = userRepository.readByUserName(user.getName());
        } catch (AppException e) {
            e.getError();
        }
    }

    @Test
    public void testUpdateUserAddress(){
        String testAddress = "TestAddress";
        try {
            userRepository.update(userBeforeTest, testAddress);
            User userAfterUpdating = userRepository.readByUserName(userBeforeTest.getName());

            SoftAssert checker = new SoftAssert();

            checker.assertEquals(
                    userAfterUpdating.getId(),
                    userBeforeTest.getId());
            checker.assertEquals(
                    userAfterUpdating.getName(),
                    userBeforeTest.getName());
            checker.assertEquals(
                    userAfterUpdating.getAddress(),
                    testAddress);
            checker.assertAll();

        } catch (AppException e) {
            e.getError();
        }
    }

    @AfterMethod
    private void deleteTestUserAfterTest(){
        try {
            userRepository.deleteByUserName(userBeforeTest.getName());
        } catch (AppException e) {
            e.getError();
        }
    }
}
