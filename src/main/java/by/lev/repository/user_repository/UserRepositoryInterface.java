package by.lev.repository.user_repository;

import by.lev.app_exception.AppException;
import by.lev.domain.User;
import by.lev.repository.CRUDOperable;

import java.sql.SQLException;

public interface UserRepositoryInterface extends CRUDOperable<User, Integer> {

    User readByUserName(String userName) throws AppException;

    void deleteByUserName(String userName) throws AppException;

    void update(User user, String value) throws AppException;

}
