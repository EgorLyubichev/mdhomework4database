package by.lev.repository;

import by.lev.domain.User;

import java.sql.SQLException;

public interface UserRepositoryInterface extends CRUDOperable<User, Integer>{

    void delete(String name) throws SQLException;

}
