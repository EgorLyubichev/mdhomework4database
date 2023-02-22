package by.lev.repository;

import java.sql.SQLException;
import java.util.List;

public interface CRUDOperable<T, N> {

    void create(T t) throws SQLException;

    T read(N value) throws SQLException;

    List<T> readAll() throws SQLException;

    void update(T t, N value) throws SQLException;

    void delete(N value) throws SQLException;

}
