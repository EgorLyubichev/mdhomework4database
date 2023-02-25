package by.lev.repository;

import java.util.List;

public interface CRUDOperable<T, N> {

    void create(T t) throws Exception;

    T read(N value) throws Exception;

    List<T> readAll() throws Exception;

//    void update(T t, String value) throws Exception;

    void delete(N value) throws Exception;

}
