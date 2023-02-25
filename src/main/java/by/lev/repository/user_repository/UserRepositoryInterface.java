package by.lev.repository.user_repository;

import by.lev.domain.User;
import by.lev.repository.CRUDOperable;

import java.sql.SQLException;

public interface UserRepositoryInterface extends CRUDOperable<User, Integer> {

    User read(String userName) throws SQLException;

    void delete(String userName) throws SQLException;

    void update(User user, String value) throws Exception;

}
