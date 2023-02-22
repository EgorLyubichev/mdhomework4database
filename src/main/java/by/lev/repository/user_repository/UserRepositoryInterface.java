package by.lev.repository.user_repository;

import by.lev.domain.User;
import by.lev.repository.CRUDOperable;

import java.sql.SQLException;

public interface UserRepositoryInterface extends CRUDOperable<User, Integer> {

    void delete(String name) throws SQLException;

}
