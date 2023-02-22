package by.lev;

import by.lev.domain.User;
import by.lev.repository.UserRepository;
import by.lev.repository.UserRepositoryInterface;

import java.sql.SQLException;

public class Test {
    public static void main(String[] args) throws SQLException {


        UserRepositoryInterface userRep = new UserRepository();

       userRep.delete("Mike");
    }
}
